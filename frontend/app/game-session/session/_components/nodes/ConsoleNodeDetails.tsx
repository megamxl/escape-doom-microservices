import React from 'react';
import {ConsoleNodeSpecificsDTO} from "@/app/gen/data";
import {Divider, Paper, Stack, Typography} from "@mui/material";
import {grey} from "@mui/material/colors";
import {Code, InfoOutlined} from "@mui/icons-material";

const ConsoleNodeDetails = ({specifics, desc}: { specifics: ConsoleNodeSpecificsDTO, desc: string }) => {
    const {return_description, example, constraints} = specifics
    return (
        <>
            <Stack spacing={2} mb={2}>
                <Stack direction="row" spacing={1} alignItems="center" color={grey[600]}>
                    <InfoOutlined sx={{fontSize: "1.75rem"}}/>
                    <Typography fontWeight="bold"> Object description </Typography>
                </Stack>
                <Typography> {desc} </Typography>
            </Stack>
            <Paper className="px-4 py-8" elevation={6}>
                <Stack direction="column" className="h-full" spacing={2}>
                    <div>
                        <Stack direction="row" spacing={1} alignItems="center" mb={2}>
                            <Code />
                            <Typography fontWeight="bold"> Code specifics </Typography>
                        </Stack>
                        <Typography variant="h6" fontWeight="bold" mb={0.5}> Return </Typography>
                        <Typography> {return_description} </Typography>
                        <Divider/>
                    </div>

                    <div>
                        <Typography variant="h6" fontWeight="bold" mb={0.5}> Constraints </Typography>
                        <Typography> {constraints} </Typography>
                        <Divider/>
                    </div>

                    <div>
                        <Typography variant="h6" fontWeight="bold" mb={0.5}> Example </Typography>
                        <Typography> {example} </Typography>
                    </div>
                </Stack>
            </Paper>
        </>
    )
}

export default ConsoleNodeDetails;