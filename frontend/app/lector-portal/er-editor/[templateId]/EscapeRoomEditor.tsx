'use client'

import React, {useEffect, useState} from 'react';
import {Divider, Grid2 as Grid, Paper, Skeleton, Stack, Typography} from "@mui/material";
import AddBoxOutlinedIcon from '@mui/icons-material/AddBoxOutlined';
import {TemplateDTO, useGetTemplateHook} from "@/app/gen/data";
import Level from "@/app/lector-portal/er-editor/[templateId]/_components/Level.tsx";
import {grey} from "@mui/material/colors";

type EditorProps = {
    templateId: string
}

export type RemoveLevelProps = {
    levelId: string
}

const EscapeRoomEditor = ({templateId}: EditorProps) => {
    const {data, isError, isLoading} = useGetTemplateHook({templateId: templateId})

    const [template, setTemplate] = useState<TemplateDTO>()

    useEffect(() => {
        console.log(data)
        setTemplate(data)
    }, [data]);

    const removeLevel = ({levelId}: RemoveLevelProps) => {

    }

    if (isLoading || !template) return <Skeleton variant="rectangular" width="100%" height="100vh"/>;

    return (
        <Grid container gap={4} p={2} height={'100vh'} style={{backgroundColor: '#121212'}}>
            <Grid size={{xs: 12, md: 3}}>
                <Stack gap={4} style={{backgroundColor: '#1e1e1e'}} height={"100%"} px={4} py={2}>
                    <Stack direction={'row'} justifyContent={"space-between"}>
                        <Typography variant={"h6"}> {template.name} </Typography>
                    </Stack>

                    <Stack>
                        <Typography variant={'h6'}> Levels </Typography>
                        <Divider/>

                        {template.levels?.map(level => {
                            return (
                                <Level title={level.name!} onRemove={removeLevel}/>
                            )
                        })}

                        <Paper>
                            <Stack direction={"row"} alignItems={"center"} spacing={1} sx={{color: grey[600]}}>
                                <AddBoxOutlinedIcon/>
                                <Typography variant={"h6"} fontWeight={"bold"}> Add Level </Typography>
                            </Stack>
                        </Paper>


                        {/*    <Accordion>*/}
                        {/*        <AccordionSummary expandIcon={<ExpandMoreIcon/>}>*/}
                        {/*            Classroom*/}
                        {/*        </AccordionSummary>*/}
                        {/*        <AccordionDetails>*/}
                        {/*            <Stack direction={'row'} justifyContent={"space-between"}>*/}
                        {/*                <Typography> MainScene </Typography>*/}
                        {/*                <div>*/}
                        {/*                    <IconButton> <CloseIcon/> </IconButton>*/}
                        {/*                    <IconButton> <SwapHorizIcon/> </IconButton>*/}
                        {/*                </div>*/}
                        {/*            </Stack>*/}
                        {/*        </AccordionDetails>*/}
                        {/*    </Accordion>*/}

                    </Stack>

                </Stack>

            </Grid>
            <Grid size='grow' style={{backgroundColor: '#1e1e1e'}}>

            </Grid>
        </Grid>
    );
};

export default EscapeRoomEditor;