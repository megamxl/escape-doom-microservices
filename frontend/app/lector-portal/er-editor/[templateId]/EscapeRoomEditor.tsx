'use client'

import React, {useEffect, useState} from 'react';
import {Button, Grid2 as Grid, Skeleton, Stack, Typography} from "@mui/material";
import AddBoxOutlinedIcon from '@mui/icons-material/AddBoxOutlined';
import {
    TemplateDTO,
    useCreateLevelHook,
    useDeleteLevelHook,
    useGetTemplateHook,
    usePutTemplateHook
} from "@/app/gen/data";
import Level from "@/app/lector-portal/er-editor/[templateId]/_components/Level.tsx";
import {grey} from "@mui/material/colors";
import {useRouter} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";
import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import SaveIcon from '@mui/icons-material/Save';
import DnDNode from "@/app/lector-portal/er-editor/[templateId]/_components/DnDNode.tsx";
import {DndContext, DragEndEvent} from "@dnd-kit/core";
import DnDDroppable from "@/app/lector-portal/er-editor/[templateId]/_components/DnDDroppable.tsx";
import {LevelDTO, SceneDTO} from "@/app/gen/player";

type EditorProps = {
    templateId: string
}

const EscapeRoomEditor = ({templateId}: EditorProps) => {
    const router = useRouter();

    const {data, isLoading} = useGetTemplateHook({templateId: templateId})
    const {mutate: updateTemplate} = usePutTemplateHook()
    const {mutate: deleteLevel} = useDeleteLevelHook()
    const {mutate: createLevel} = useCreateLevelHook()

    const [template, setTemplate] = useState<TemplateDTO>()
    const [selectedScene, setSelectedScene] = useState<SceneDTO>()

    useEffect(() => {
        if (!data || !data.levels) return;
        setTemplate(data)

        const firstLevel = data.levels.find(level => level.level_sequence === 1)
        if (!firstLevel || !firstLevel.scenes) return;

        setSelectedScene(firstLevel.scenes.find(scene => scene.scene_sequence === 1))

    }, [data]);

    useEffect(() => {
        console.log("Template", template)
        saveTemplate()
    }, [template]);

    const removeLevel = (levelId: string) => {
        deleteLevel({levelId: levelId}, {
            onSuccess: () => {
                console.log("Deletion successful")
                setTemplate({...template, levels: template?.levels!.filter(l => l.level_id !== levelId)})
            },
            onError: (error) => {
                console.error("Something went wrong!: ", error)
            }
        })
    }

    const updateLevel = (level: LevelDTO) => {
        if (!template || !template.levels) return;
        console.log("Received toUpdate level:", level)

        setTemplate(prev => ({
            ...prev,
            levels: prev?.levels!.map(l =>
                l.level_id === level.level_id ? {...l, ...level} : l
            )
        }))
        saveTemplate()
    }

    const addLevel = () => {
        if (!template || !template.levels) return;

        createLevel({
            data: {
                template_id: templateId!, level_sequence: (template.levels.length + 1), name: 'New level'
            }
        }, {
            onSuccess: (addedLevel) => {
                setTemplate({...template, levels: [...template.levels!, addedLevel]})
            },
            onError: (error) => {
                console.log(error)
            }
        })
    }

    const saveTemplate = () => {
        if (!template) return;
        updateTemplate({
            templateId: template.template_id ?? "",
            data: {
                name: template.name,
                description: template.description,
                levels: template.levels
            }
        }, {
            onSuccess: (updated) => {
                console.log(updated.message)
            },
            onError: (error) => {
                console.error('Saving template failed', error)
            }
        })
    }

    const handleDragEnd = (event: DragEndEvent) => {
        console.log('Event:', event)
    }

    if (isLoading || !template) return <Skeleton variant="rectangular" width="100%" height="100vh"/>;

    return (
        <>
            <Grid spacing={4} container p={2} height={'100vh'} style={{backgroundColor: '#121212'}}>
                <Grid spacing={4} size={{xs: 12, md: 3}}>
                    <Stack style={{backgroundColor: '#1e1e1e'}} height={"100%"} px={4} py={2}>
                        <Typography variant="h5"> {template.name} </Typography>

                        <Stack spacing={2}>
                            <Typography variant={'h5'}> Levels </Typography>

                            {template.levels?.map(level => {
                                return (
                                    <Level
                                        key={level.level_id}
                                        level={level}
                                        onRemove={removeLevel}
                                        onChange={updateLevel}
                                    />
                                )
                            })}

                            <Button onClick={addLevel} fullWidth
                                    sx={{color: grey[50], p: 0, justifyContent: "flex-start", height: '2rem'}}>
                                <Stack direction={"row"} spacing={1} sx={{color: grey[600]}} alignItems={"center"}>
                                    <AddBoxOutlinedIcon sx={{marginTop: "auto"}}/>
                                    <Typography variant={"h6"} fontWeight={"bold"}>
                                        Add Level
                                    </Typography>
                                </Stack>
                            </Button>
                        </Stack>

                        <Stack direction={"row"} spacing={2} mt={"auto"}>
                            <Button onClick={saveTemplate} fullWidth variant={"contained"} startIcon={<SaveIcon/>}>
                                <strong> Save </strong>
                            </Button>
                            <Button size={"large"} onClick={() => router.push(LECTOR_PORTAL_APP_PATHS.DASHBOARD)}
                                    fullWidth
                                    variant={"contained"} startIcon={<DirectionsRunIcon/>}>
                                <strong> Leave </strong>
                            </Button>
                        </Stack>
                    </Stack>
                </Grid>
                <Grid size='grow' style={{backgroundColor: '#1e1e1e'}} className="p-4 relative">
                    <DndContext autoScroll={false} onDragEnd={handleDragEnd}>
                        <div id="NodesContainer"
                             className="p-2 rounded-lg z-10 bg-[#2e2e2e] flex justify-center gap-4 absolute bottom-4 left-[50%] translate-x-[-50%]">
                            <DnDNode nodeType={"ZOOM"}/>
                            <DnDNode nodeType={"DETAIL"}/>
                            <DnDNode nodeType={"CONSOLE"}/>
                            <DnDNode nodeType={"STORY"}/>
                        </div>
                        <DnDDroppable bgImageUrl={selectedScene?.background_image_uri}
                                      nodeList={selectedScene?.nodes ?? []}>
                        </DnDDroppable>
                    </DndContext>
                </Grid>
            </Grid>
        </>
    );
};

export default EscapeRoomEditor;