import React from 'react';
import IconButton from "@mui/material/IconButton";
import {NodeDTO} from "@/app/gen/player";
import {purple} from "@mui/material/colors";
import {Reply, Visibility} from "@mui/icons-material";
import {ZoomNodeSpecificsDTO} from "@/app/gen/data";

type ZoomNodeProps = {
    node: NodeDTO
    onZoomChangeScene?: (targetSceneId: string) => void
}

const ZoomNode = ({node, onZoomChangeScene}: ZoomNodeProps) => {
    const position = node.position
    const zoomSpecifics = node.node_specifics as ZoomNodeSpecificsDTO

    const changeScene = () => {
        if (onZoomChangeScene && zoomSpecifics.linked_scene_id) {
            onZoomChangeScene(zoomSpecifics.linked_scene_id)
        } else {
            console.warn("Target scene ID not provided or callback missing.")
        }
    }

    return (
        <IconButton
            onClick={changeScene}
            style={{
                position: "absolute",
                top: `${position?.top_percentage}%`,
                left: `${position?.left_percentage}%`,
                color: "#fff",
                backgroundColor: purple[400],
                borderRadius: "50%",
                width: "clamp(1.5rem, 2vw, 3rem)",
                height: "clamp(1.5rem, 2vw, 3rem)"
            }}>
            { zoomSpecifics.linked_scene_id === null || undefined ? <Reply /> : <Visibility/> }
        </IconButton>
    );
};

export default ZoomNode;