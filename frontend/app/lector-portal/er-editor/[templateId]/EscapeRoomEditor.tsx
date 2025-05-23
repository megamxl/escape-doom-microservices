'use client'

import React, {useEffect, useState} from 'react';
import {Button, Grid, Skeleton, Stack, Typography} from "@mui/material";
import AddBoxOutlinedIcon from '@mui/icons-material/AddBoxOutlined';
import {TemplateDTO, useCreateLevelHook, useGetTemplateHook, useUpdateTemplateHook} from "@/app/gen/data";
import Level from "@/app/lector-portal/er-editor/[templateId]/_components/Level.tsx";
import {grey} from "@mui/material/colors";
import {useRouter} from "next/navigation";
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
    const {mutate: updateTemplate} = useUpdateTemplateHook()
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

    const handleSceneSelection = (sceneId: string, levelId: string) => {
        if (!template || !template.levels) return;

        const selectedLevel = template.levels.find(l => l.level_id && l.level_id === levelId)

        if (!selectedLevel || !selectedLevel.scenes) return;

        const newScene = selectedLevel.scenes.find(s => s.scene_id === sceneId)
        setSelectedScene(newScene)
    }

    const handleLevelDeletion = (levelId: string) => {
        setTemplate(prev => ({
            ...prev,
            levels: prev?.levels!.filter(lvl => lvl.level_id !== levelId)
        }))
    }

    const handleLevelUpdate = (level: LevelDTO) => {
        setTemplate(prev => ({
            ...prev,
            levels: [...prev?.levels?.filter(l => l.level_id !== level.level_id) ?? [], level]
        }))
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
                <Grid spacing={4} size={{xs: 12, md: 3}} height='100%'>
                    <Stack
                        style={{backgroundColor: '#1e1e1e'}}
                        height={"100%"}
                        px={4}
                        py={2}
                        spacing={2}
                    >
                        <Typography variant="h5"> {template.name} </Typography>

                        <Stack
                            spacing={2}
                            sx={{
                                flexGrow: 1,
                                overflowY: 'auto',
                                overflowX: 'hidden',
                                minHeight: 0,
                            }}
                        >
                            {template.levels
                                ?.sort((l1, l2) => l1.level_sequence! - l2.level_sequence!)
                                .map(level => (
                                        <Level
                                            key={level.level_id}
                                            level={level}
                                            onLevelUpdate={handleLevelUpdate}
                                            onDeletion={handleLevelDeletion}
                                            onSceneSelection={handleSceneSelection}
                                        />
                                    )
                                )}

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
                            <Button size={"large"} onClick={() => router.back()}
                                    fullWidth
                                    variant={"contained"} startIcon={<DirectionsRunIcon/>}>
                                <strong> Leave </strong>
                            </Button>
                        </Stack>
                    </Stack>
                </Grid>
                <Grid size='grow' style={{backgroundColor: '#1e1e1e'}} className="p-4 relative h-full">
                    <DndContext autoScroll={false} onDragEnd={handleDragEnd}>
                        <div id="NodesContainer"
                             className="p-2 rounded-lg z-10 bg-[#2e2e2e] flex justify-center gap-4 absolute bottom-4 left-[50%] translate-x-[-50%]">
                            <DnDNode nodeType={"ZOOM"}/>
                            <DnDNode nodeType={"DETAIL"}/>
                            <DnDNode nodeType={"CONSOLE"}/>
                            <DnDNode nodeType={"STORY"}/>
                        </div>
                        {selectedScene && <DnDDroppable key={selectedScene.scene_id} selectedScene={selectedScene} />}
                    </DndContext>
                </Grid>
            </Grid>
        </>
    );
};

export default EscapeRoomEditor;