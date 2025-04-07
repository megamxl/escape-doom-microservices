'use client'

import React, {useState} from 'react';
import {Box, Collapse, IconButton, Stack, Typography} from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import CloseIcon from "@mui/icons-material/Close";
import {LevelDTO} from "@/app/gen/data";
import Scene from "@/app/lector-portal/er-editor/[templateId]/_components/Scene.tsx";
import AddScene from "@/app/lector-portal/er-editor/[templateId]/_components/AddScene.tsx";

type LevelProps = {
    level: LevelDTO,
    onRemove: (levelId: string) => void;
}

const removeScene = (sceneId: string) => {
    console.log("I should remove a scene")
}

const Level = ({level, onRemove}: LevelProps) => {
    const [expanded, setExpanded] = useState(true);
    const {level_id, name, scenes} = level

    return (
        <Box>
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={1}>
                <Stack direction="row" alignItems="center" spacing={1}>
                    <IconButton
                        size="small"
                        onClick={() => setExpanded(!expanded)}
                        sx={{
                            color: 'white',
                            transform: expanded ? 'rotate(0deg)' : 'rotate(180deg)',
                            transition: '0.2s'
                        }}
                    >
                        <ExpandMoreIcon/>
                    </IconButton>
                    <Typography variant={"h5"}> {name} </Typography>
                </Stack>
                <IconButton size={"small"} onClick={() => onRemove(level_id!)}>
                    <CloseIcon/>
                </IconButton>
            </Stack>
            <Collapse in={expanded} timeout={"auto"} unmountOnExit>
                <Box ml={5}>
                    <Stack spacing={0.5}>
                        {scenes?.map(scene => (<Scene key={scene.scene_id} scene={scene} onDelete={removeScene}/>))}
                        <AddScene />
                    </Stack>
                </Box>
            </Collapse>
        </Box>
    );
};

export default Level;