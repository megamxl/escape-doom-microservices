'use client'

import React from 'react';
import {NodeType} from "@/app/gen/player";
import {useDraggable} from "@dnd-kit/core";
import IconButton from "@mui/material/IconButton";
import {nodeTypeClassMapper} from "@/app/game-session/session/_components/nodes/Node.tsx";
import {CSS} from '@dnd-kit/utilities';

type DnDNodeProps = {
    nodeType: NodeType
}

const DnDNode = ({nodeType}: DnDNodeProps) => {
    const {attributes, listeners, setNodeRef, transform} = useDraggable({
        id: nodeType
    })

    const {icon: Icon, styling} = nodeTypeClassMapper[nodeType]
    const dragStyle = {
        transform: CSS.Translate.toString(transform),
        backgroundColor: styling.color,
    }

    return (
        <>
            <IconButton
                ref={setNodeRef}
                style={dragStyle}
                {...listeners}
                {...attributes}
            >
                <Icon fontSize={"small"}/>
            </IconButton>
        </>
    );
};

export default DnDNode;