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

type EditorProps = {
    templateId: string
}

const EscapeRoomEditor = ({templateId}: EditorProps) => {
    const {data, isError, isLoading, refetch} = useGetTemplateHook({templateId: templateId})

    const {mutate: updateTemplate} = usePutTemplateHook()
    const {mutate} = useDeleteLevelHook()
    const {mutate: createLevel} = useCreateLevelHook()

    const router = useRouter();

    const [template, setTemplate] = useState<TemplateDTO>()

    useEffect(() => {
        console.log(data)
        setTemplate(data)
    }, [data]);

    const removeLevel = (levelId: string) => {
        //INFO: This works but cascading delete is still broken so this doesn't throw
        mutate({levelId: levelId}, {
            onSuccess: (response) => {
                console.log("Deletion successful")
                setTemplate({...template, levels: template?.levels!.filter(l => l.level_id !== levelId)})
            },
            onError: (error) => {
                console.error("Something went wrong!: ", error)
            }
        })
    }

    const saveTemplate = () => {
        updateTemplate({
            templateId: template?.template_id!, data: {
                name: template?.name,
                description: template?.description,
                levels: template?.levels
            }
        }, {
            onSuccess: (updated) => {
                console.log('Saved template!', template)
            },
            onError: (error) => {
                console.error('Saving template failed', error)
            }
        })
    }

    const addLevel = () => {
        createLevel({
            data: {
                template_id: templateId!, level_sequence: (template?.levels?.length! + 1), name: 'New level'
            }
        }, {
            onSuccess: (addedLevel) => {
                setTemplate({...template, levels: [...template?.levels!, addedLevel]})
            },
            onError: (error) => {

            }
        })
    }

    if (isLoading || !template) return <Skeleton variant="rectangular" width="100%" height="100vh"/>;

    return (
        <Grid spacing={4} container p={2} height={'100vh'} style={{backgroundColor: '#121212'}}>
            <Grid spacing={4} size={{xs: 12, md: 3}}>
                <Stack style={{backgroundColor: '#1e1e1e'}} height={"100%"} px={4} py={2}>
                    <Stack direction={'row'} justifyContent={"space-between"}>
                        <Typography variant={"h6"}> {template.name} </Typography>
                    </Stack>

                    <Stack spacing={2}>
                        <Typography variant={'h4'}> Levels </Typography>

                        {template.levels?.map(level => {
                            return (
                                <Level key={level.level_id} level={level} onRemove={removeLevel}/>
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
                        <Button size={"large"} onClick={() => router.push(LECTOR_PORTAL_APP_PATHS.DASHBOARD)} fullWidth
                                variant={"contained"} startIcon={<DirectionsRunIcon/>}>
                            <strong> Leave </strong>
                        </Button>
                    </Stack>
                </Stack>
            </Grid>
            <Grid size='grow' style={{backgroundColor: '#1e1e1e'}}>

            </Grid>
        </Grid>
    );
};

export default EscapeRoomEditor;