import React from 'react';
import {Button, Stack, Typography} from '@mui/material';
import {grey} from "@mui/material/colors";
import AddBoxOutlinedIcon from "@mui/icons-material/AddBoxOutlined";

const Scene = () => {

    const openScenePopup = () => {

    }

    return (
        <Button onClick={openScenePopup} fullWidth sx={{ color: grey[50], p: 0, justifyContent: "flex-start", height: '2rem' }}>
            <Stack direction={"row"} spacing={1} sx={{ cursor: 'pointer', color: grey[600] }}>
                <AddBoxOutlinedIcon fontSize={"small"}/>
                <Typography sx={{color: grey[600]}} fontWeight={"bold"}> Add Scene </Typography>
            </Stack>
        </Button>
    );
};

export default Scene;