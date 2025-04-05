import React from 'react';
import {Accordion, AccordionDetails, AccordionSummary, Divider, Grid2 as Grid, Stack, Typography} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import SwapHorizIcon from '@mui/icons-material/SwapHoriz';
import CloseIcon from '@mui/icons-material/Close';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

type EditorProps = {
    templateId: string
}

const EscapeRoomEditor = ({templateId}: {templateId: string}) => {
    return (
        <Grid container gap={4} height={'100vh'} style={{backgroundColor: '#121212'}}>
            <Grid size={{ xs: 12, md: 3}}>
                <Stack gap={4} style={{backgroundColor: '#1e1e1e'}} height={"100%"} px={4}>
                    <Stack direction={'row'} justifyContent={"space-between"} >
                        <Typography variant={"h6"}> EscapeRooomName </Typography>
                        <Typography variant={"h6"}> Topic </Typography>
                        <Typography> {templateId} </Typography>
                    </Stack>

                    <Stack>
                        <Typography variant={'h6'}> Levels </Typography>
                        <Divider />

                        <Accordion>
                            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                                Classroom
                            </AccordionSummary>
                            <AccordionDetails>
                                <Stack direction={'row'} justifyContent={"space-between"}>
                                    <Typography> MainScene </Typography>
                                    <div>
                                        <IconButton> <CloseIcon /> </IconButton>
                                        <IconButton> <SwapHorizIcon /> </IconButton>
                                    </div>
                                </Stack>
                            </AccordionDetails>
                        </Accordion>

                    </Stack>

                </Stack>

            </Grid>
            <Grid size='grow' style={{backgroundColor: '#1e1e1e'}}>

            </Grid>
        </Grid>
    );
};

export default EscapeRoomEditor;