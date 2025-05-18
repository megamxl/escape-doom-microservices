'use client'

import React from 'react';
import {useGetAllTemplatesHook} from "@/app/gen/data";
import {Card, CardContent, Grid, Typography} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {Edit} from '@mui/icons-material';
import {useRouter} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";

const TemplateExplorer = () => {

    const {data, error} = useGetAllTemplatesHook()

    const router = useRouter()

    return (
        <>
            <Grid width={"70%"} mx={"auto"} mt={6} container spacing={3}>
                {
                    data && !error &&
                    data.map(template => {
                        if (!template.levels || !template.levels[0].scenes) return;

                        return (
                            <Grid key={template.template_id} size={6}>
                                <Card>
                                    <img
                                        className={"object-cover max-h-80 w-full"}
                                        src={`${template.levels[0].scenes[0].background_image_uri}`}

                                    />
                                    <CardContent>
                                        <div className="flex flex-row justify-between">
                                            <div>
                                                <Typography variant="h6"> {template.name} </Typography>
                                                <Typography> {template.description} </Typography>
                                            </div>
                                            <IconButton onClick={() => router.push(`${LECTOR_PORTAL_APP_PATHS.EDITOR}/${template.template_id}`)}>
                                                <Edit />
                                            </IconButton>
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