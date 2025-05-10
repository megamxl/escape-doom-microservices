import React from 'react';
import IconButton from "@mui/material/IconButton";
import {NodeDTO} from "@/app/gen/player";
import {purple} from "@mui/material/colors";
import {Visibility} from "@mui/icons-material";
import {ZoomNodeSpecificsDTO} from "@/app/gen/data";

const ZoomNode = ({node}: {node: NodeDTO}) => {
    const position = node.position
    const zoomSpecifics = node.node_specifics as ZoomNodeSpecificsDTO

    const changeScene = () => {
        console.log('ZoomSpecifics: ', zoomSpecifics)
        //TODO: Make redirect call and load new scene - Probably new endpoint needed
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
            <Visibility />
        </IconButton>
    );
};

export default ZoomNode;