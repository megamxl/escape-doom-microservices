'use client'

import React, {useState} from 'react';
import {Box, Button, Collapse, IconButton, Stack, Typography} from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import CloseIcon from "@mui/icons-material/Close";
import {LevelDTO, useCreateSceneHook, useDeleteSceneHook} from "@/app/gen/data";
import Scene from "@/app/lector-portal/er-editor/[templateId]/_components/Scene.tsx";
import InlineEditableText from "@/app/_components/InlineEditableText.tsx";
import {grey} from "@mui/material/colors";
import AddBoxOutlinedIcon from "@mui/icons-material/AddBoxOutlined";

type LevelProps = {
    level: LevelDTO,
    onRemove: (levelId: string) => void
    onChange: (level: LevelDTO) => void
}

const Level = ({level: prop, onRemove, onChange}: LevelProps) => {
    const {mutate: createScene} = useCreateSceneHook()
    const {mutate: deleteScene} = useDeleteSceneHook()

    const [expanded, setExpanded] = useState(true);
    const [level, setLevel] = useState(prop)

    const addScene = (level: LevelDTO) => {
        if (!level.scenes) return;

        createScene({
                data: {
                    scene_sequence: level.scenes.length + 1,
                    level_id: level.level_id,
                    background_image_uri: undefined,
                    name: 'New Scene'
                }
            },
            {
                onSuccess: (addedScene) => {
                    if (!level.scenes) return;
                    setLevel({...level, scenes: [...level.scenes, addedScene]})
                },
                onError: (addSceneError) => {
                    console.error("Couldn't create scene:", addSceneError)
                }
            })
    }

    const removeScene = (sceneId: string) => {
        console.log(level.scenes, sceneId)
        deleteScene({sceneId: sceneId}, {
            onSuccess: (sceneDeleted) => {
                if (!level.scenes) return;
                setLevel({...level, scenes: level.scenes.filter(scene => scene.scene_id !== sceneId) })
                console.log("Deleted scene", sceneDeleted)
            },
            onError: (err) => {
                console.error("Failed to remove scene", err)
            }
        })
    }

    const handleSceneAdd = () => {
        addScene(level)
    }

    const handleNameChange = async (newName: string) => {
        setLevel({...level, name: newName})
        onChange({...level, name: newName})
    }

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

                    <InlineEditableText variant={"h5"} value={level.name!} onSave={handleNameChange}/>
                </Stack>
                <IconButton size={"small"} onClick={() => onRemove(level.level_id!)}>
                    <CloseIcon/>
                </IconButton>
            </Stack>
            <Collapse in={expanded} timeout={"auto"} unmountOnExit>
                <Box ml={5}>
                    <Stack spacing={0.5}>
                        {level.scenes?.map(scene => (
                            <Scene key={scene.scene_id} scene={scene} onDelete={removeScene}/>)
                        )}
                        <Button onClick={handleSceneAdd} fullWidth
                                sx={{color: grey[50], p: 0, justifyContent: "flex-start", height: '2rem'}}>
                            <Stack direction={"row"} spacing={1} sx={{cursor: 'pointer', color: grey[600]}}>
                                <AddBoxOutlinedIcon fontSize={"small"}/>
                                <Typography sx={{color: grey[600]}} fontWeight={"bold"}> Add Scene </Typography>
                            </Stack>
                        </Button>
                    </Stack>
                </Box>
            </Collapse>
        </Box>
    );
};

export default Level;