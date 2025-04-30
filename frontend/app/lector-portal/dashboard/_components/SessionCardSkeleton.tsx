import React from 'react';
import {Box, Paper, Skeleton, Stack, Typography} from "@mui/material";

const SessionCardSkeleton = () => {
    const animationType = "wave"
    return (
        <Box>
            <Paper style={{
                minHeight: '200px',
                position: 'relative',
                display: 'flex',
                flexDirection: 'column',
                marginBottom: '0.5rem'
            }}>
                <Skeleton animation={animationType} style={{position: "absolute", top: '10px', right: '10px'}} variant={"circular"} width={40} height={40} />
                <Stack style={{flexGrow: 1, height: '100%'}} justifyContent={"center"} alignItems={"center"}>
                    <Typography width={"60%"} textAlign={"center"} variant="h4">
                        <Skeleton animation={animationType} />
                    </Typography>
                </Stack>
            </Paper>
            <Skeleton animation={animationType} variant={"rounded"} />
        </Box>
    );
};

export default SessionCardSkeleton;