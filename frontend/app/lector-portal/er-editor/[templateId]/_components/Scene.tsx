import React, {useState} from 'react';

import AspectRatioIcon from '@mui/icons-material/AspectRatio';
import {Button, Stack} from '@mui/material';
import {SceneDTO} from "@/app/gen/player";
import CloseIcon from "@mui/icons-material/Close";
import IconButton from "@mui/material/IconButton";
import SwapHorizIcon from '@mui/icons-material/SwapHoriz';
import {grey} from "@mui/material/colors";
import {useDeleteSceneHook, useUpdateSceneHook} from "@/app/gen/data";
import InlineEditableText from "@/app/_components/InlineEditableText.tsx";

type SceneProps = {
    scene: SceneDTO
    onDeletion: (sceneId: string) => void
    onSelection: (sceneId: string) => void
}

const Scene = ({scene: prop, onDeletion, onSelection}: SceneProps) => {
    const [scene, setScene] = useState<SceneDTO>(prop)

    const {mutate: deleteScene} = useDeleteSceneHook()
    const {mutate: updateScene} = useUpdateSceneHook()

    const removeScene = () => {
        if (!scene.scene_id) return

        deleteScene({sceneId: scene.scene_id}, {
            onSuccess: () => {
                if (!scene.scene_id) return;
                onDeletion(scene.scene_id)
            },
            onError: (err) => {
                console.error("Failed to remove scene", err)
            }
        })
        onDeletion(scene.scene_id)
    }

    const handleNameChange = async (newName: string) => {
        if (!scene.scene_id) return

        setScene(prevState => ({ ...prevState, name: newName}))

        updateScene({
            sceneId: scene.scene_id,
            data: { ...scene, name: newName }
        }, {
            onSuccess: (response) => {
                setScene(response)
                console.log("Scene updated", response)
            },
            onError: (error) => {
                console.error("Updating scene failed:", error)
            }
        })
    }

    const handleSceneSelection = () => {
        console.log(scene.scene_id)
        onSelection(scene.scene_id ?? "")
    }

    return (
        <Stack direction={"row"} justifyContent={"space-between"} alignContent={"center"}>
            <Button
                onClick={handleSceneSelection}
                fullWidth={true}
                sx={{color: grey[50], p: 0, justifyContent: "flex-start"}}
            >
                <Stack direction={"row"} spacing={1} alignItems="center" className="truncate">
                    <AspectRatioIcon fontSize={"small"}/>
                    <InlineEditableText
                        variant={"body1"}
                        value={scene.name ?? ""}
                        onSave={handleNameChange}
                        inputProps={{ fullWidth: true }}
                    />
                </Stack>
            </Button>
            <Stack direction={"row"} spacing={0.5}>
                <IconButton size={"small"} onClick={() => console.log("I should open a card for swapping")}>
                    <SwapHorizIcon/>
                </IconButton>
                <IconButton size={"small"} onClick={removeScene}>
                    <CloseIcon/>
                </IconButton>
            </Stack>
        </Stack>
    );
};

export default Scene;