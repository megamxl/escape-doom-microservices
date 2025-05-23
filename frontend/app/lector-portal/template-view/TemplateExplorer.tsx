'use client'

import React, {useEffect, useState} from 'react';
import {TemplateDTO, useDeleteTemplateHook, useGetAllTemplatesHook} from "@/app/gen/data";
import {Card, CardContent, Grid, Typography} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {Delete, Edit} from '@mui/icons-material';
import {useRouter} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";

const TemplateExplorer = () => {

    const {data, error} = useGetAllTemplatesHook()
    const {mutate: deleteTemplate} = useDeleteTemplateHook()

    const [templates, setTemplates] = useState<TemplateDTO[]>([])

    useEffect(() => {
        console.log(data)
        if (data) {
            setTemplates(data)
        }
    }, [data]);

    const router = useRouter()

    const handleDeletion = (templateId: string) => {
        deleteTemplate({templateId: templateId}, {
            onSuccess: () => {
                setTemplates(prev => prev?.filter(t => t.template_id !== templateId))
            },
            onError: (err) => {
                console.error("Couldn't delete tempalte:", err)
            }
        })
    }

    const handleEdit = (templateId: string) => {
        router.push(`${LECTOR_PORTAL_APP_PATHS.EDITOR}/${templateId}`)
        router.refresh()
    }

    return (
        <>
            <Grid width={"70%"} mx={"auto"} mt={6} container spacing={3}>
                {
                    templates && !error &&
                    templates.map(template => {
                        if (!template.levels) return;

                        const firstLevel = template.levels.find(l => l.level_sequence == 1)
                        const firstScene = firstLevel?.scenes?.find(s => s.scene_sequence == 1)

                        return (
                            <Grid key={template.template_id} size={6}>
                                <Card>
                                    <img
                                        className={"object-cover max-h-80 w-full"}
                                        src={firstScene?.background_image_uri}

                                    />
                                    <CardContent>
                                        <div className="flex flex-row justify-between">
                                            <div className="flex gap-4 items-center">
                                                <div>
                                                    <Typography variant="h6"> {template.name} </Typography>
                                                    <Typography> {template.description} </Typography>
                                                </div>
                                                <IconButton
                                                    onClick={() => handleEdit(template.template_id!) }>
                                                    <Edit/>
                                                </IconButton>
                                            </div>
                                            <div className="flex gap-3 items-center">
                                                <IconButton onClick={() => handleDeletion(template.template_id!)}>
                                                    <Delete color="error"/>
                                                </IconButton>
                                            </div>
                                        </div>
                                    </CardContent>
                                </Card>
                            </Grid>
                        )
                    })
                }

            </Grid>
        </>
    );
};

export default TemplateExplorer;