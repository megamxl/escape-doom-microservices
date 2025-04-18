import React from 'react';

import AspectRatioIcon from '@mui/icons-material/AspectRatio';
import {Button, Stack, Typography} from '@mui/material';
import {SceneDTO} from "@/app/gen/player";
import CloseIcon from "@mui/icons-material/Close";
import IconButton from "@mui/material/IconButton";
import SwapHorizIcon from '@mui/icons-material/SwapHoriz';
import {grey} from "@mui/material/colors";

type SceneProps = {
    scene: SceneDTO
    onDelete: (sceneId: string) => void
}

const Scene = ({scene, onDelete}: SceneProps) => {
    const {name, scene_id} = scene

    return (
        <Stack direction={"row"} justifyContent={"space-between"} alignContent={"center"}>
            <Button fullWidth sx={{ color: grey[50], p: 0, justifyContent: "flex-start" }}>
                <Stack direction={"row"} spacing={1}>
                    <AspectRatioIcon fontSize={"small"}/>
                    <Typography> {scene?.name} </Typography>
                </Stack>
            </Button>
            <Stack direction={"row"} spacing={0.5}>
                <IconButton size={"small"} onClick={() => console.log("I should open a card for swapping")}>
                    <SwapHorizIcon/>
                </IconButton>
                <IconButton size={"small"} onClick={() => onDelete(scene_id!)}>
                    <CloseIcon/>
                </IconButton>
            </Stack>
        </Stack>
    );
};

export default Scene;