'use client'

import React, {useState} from 'react';
import {Box, Button, Collapse, IconButton, Stack, Typography} from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import CloseIcon from "@mui/icons-material/Close";
import {LevelDTO, useCreateSceneHook, useDeleteLevelHook, useUpdateLevelHook} from "@/app/gen/data";
import Scene from "@/app/lector-portal/er-editor/[templateId]/_components/Scene.tsx";
import InlineEditableText from "@/app/_components/InlineEditableText.tsx";
import {grey} from "@mui/material/colors";
import AddBoxOutlinedIcon from "@mui/icons-material/AddBoxOutlined";

type LevelProps = {
    level: LevelDTO
    onDeletion: (levelId: string) => void
    onSceneSelection: (sceneId: string, levelId: string) => void
}

const Level = ({level: prop, onDeletion, onSceneSelection}: LevelProps) => {
    const {mutate: deleteLevel} = useDeleteLevelHook()
    const {mutate: updateLevel} = useUpdateLevelHook()
    const {mutate: createScene} = useCreateSceneHook()

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

    const handleSceneRemove = (sceneId: string) => {
        setLevel(prev => ({
            ...prev, scenes: prev.scenes?.filter(s => s.scene_id !== sceneId)
        }))
    }

    const handleLevelRemove = () => {
        if (!level.level_id) return

        deleteLevel({levelId: level.level_id}, {
            onSuccess: () => {
                if (!level.level_id) return
                onDeletion(level.level_id)
            },
            onError: (error) => {
                console.error("Something went wrong!: ", error)
            }
        })
    }

    const handleSceneSelection = (sceneId: string) => {
        onSceneSelection(sceneId, level.level_id!)
    }

    const handleSceneAdd = () => {
        addScene(level)
    }

    const handleNameChange = async (newName: string) => {
        setLevel({...level, name: newName})
        if (!level.level_id) return

        console.log("Sending", level)

        updateLevel({
            levelId: level.level_id,
            data: { ...level, name: newName }
        }, {
            onSuccess: (result) => {
                console.log("Updated level", result)
            },
            onError: (error) => {
                console.error("Updating level failed", error)
            }
        })
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

                    <InlineEditableText
                        variant={"h5"}
                        value={level.name!}
                        onSave={handleNameChange}
                    />
                </Stack>
                <IconButton size={"small"} onClick={handleLevelRemove}>
                    <CloseIcon/>
                </IconButton>
            </Stack>
            <Collapse in={expanded} timeout={"auto"} unmountOnExit>
                <Box ml={5}>
                    <Stack spacing={0.5}>
                        {level.scenes
                            ?.sort((s1, s2) => s1.scene_sequence! - s2.scene_sequence!)
                            .map(scene => (
                            <Scene
                                key={scene.scene_id}
                                scene={scene}
                                onDeletion={handleSceneRemove}
                                onSelection={handleSceneSelection}
                            />)
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