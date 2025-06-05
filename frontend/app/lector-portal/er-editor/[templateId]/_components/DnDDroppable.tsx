'use client'

import React, {ChangeEvent, useEffect, useState} from 'react';
import {useDroppable} from "@dnd-kit/core";
import {NodeDTO, SceneDTO} from '@/app/gen/player';
import {FileUpload} from "@mui/icons-material";
import {Button, Typography} from "@mui/material";
import HiddenFileUpload from "@/app/lector-portal/er-editor/[templateId]/_components/HiddenFileUpload.tsx";
import {useUploadImageToImgur} from "@/app/tanstack/imgurAxiosClient.ts";
import {useUpdateSceneHook} from "@/app/gen/data";
import NodeDraggable from "@/app/lector-portal/er-editor/[templateId]/_components/NodeDraggable.tsx";

type DroppableProps = {
    selectedScene: SceneDTO
    elements: NodeDTO[]
}

const DnDDroppable = ({selectedScene, elements}: DroppableProps) => {
    const {isOver, setNodeRef} = useDroppable({
        id: 'droppable'
    })

    const [file, setFile] = useState<File | null>(null);

    const {mutate: uploadImg} = useUploadImageToImgur()
    const {mutate: updateScene} = useUpdateSceneHook()

    useEffect(() => {
        handleUpload()
    }, [file]);

    const handleImageUpload = async (event: ChangeEvent<HTMLInputElement>) => {
        console.log("Triggered", event)
        if (event.target.files && event.target.files.length > 0) {
            console.log("Trying to set file and upload")
            setFile(event.target.files[0])
        }
    }

    const handleUpload = () => {
        if (file) {
            uploadImg(file, {
                onSuccess: (response) => {
                    console.log("File upload successful:", response)
                    updateScene({
                        sceneId: selectedScene.scene_id!,
                        data: {...selectedScene, background_image_uri: response.data.link}
                    }, {
                        onSuccess: (response) => {
                            console.log("Scene updated", response)
                        },
                        onError: (error) => {
                            console.error("Updating scene failed:", error)
                        }
                    })
                },
                onError: (error) => {
                    console.error("Upload image failed", error)
                }
            })
        }
    }

    const style = isOver ? 'border-2 border-dashed rounded-md' : ""

    return (
        <div className="h-full w-full">
            {selectedScene.background_image_uri ?
                <div className="relative w-full mx-auto">
                    <img
                        id="sceneImageContainer"
                        ref={setNodeRef}
                        src={selectedScene.background_image_uri}
                        alt="Scene Background"
                        className={`${style} w-full bg-no-repeat bg-contain z-0`}>
                    </img>
                    {elements.map((node) => {
                        return (
                            <NodeDraggable style={{
                                position: "absolute",
                                top: `${node.position?.top_percentage}%`,
                                left: `${node.position?.left_percentage}%`
                            }}
                               key={node.node_id}
                               node={node} />
                        )
                    })}
                </div> :
                <div className="border-2 border-dashed rounded-md h-full flex justify-center content-center">
                    <Button
                        component="label"
                        role={undefined}
                        tabIndex={-1}
                        fullWidth={true}
                        className="flex flex-col" sx={{color: '#fff'}}
                    >
                        <HiddenFileUpload onChange={handleImageUpload}/>
                        <FileUpload sx={{fontSize: '6rem'}}/>
                        <Typography variant="h6"> Click to add image </Typography>
                    </Button>
                </div>
            }
        </div>
    );
};

export default DnDDroppable;