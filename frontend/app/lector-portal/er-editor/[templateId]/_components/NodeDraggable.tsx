'use client'

import React, {CSSProperties, useState} from 'react';
import {NodeDTO} from "@/app/gen/player";
import {PointerSensor, useDraggable, useSensor, useSensors} from "@dnd-kit/core";
import IconButton from "@mui/material/IconButton";
import {nodeTypeClassMapper} from "@/app/game-session/session/_components/nodes/Node.tsx";
import {CSS} from '@dnd-kit/utilities';
import {Card, CardContent, CardHeader, Dialog} from "@mui/material";

type DnDNodeProps = {
    node: NodeDTO
    className?: string;
    style?: React.CSSProperties
}

const NodeDraggable = ({node, className, style}: DnDNodeProps) => {
    const {
        attributes,
        listeners,
        setNodeRef,
        transform,
    } = useDraggable({
        id: node.node_id!,
        data: {
            node: node
        },
    })
    const [isOpen, setIsOpen] = useState(false)

    if (!node || !node.node_specifics) return null

    const {icon: Icon, styling} = nodeTypeClassMapper[node.node_specifics.node_type!]
    const dragStyle: CSSProperties | undefined = {
        position: "relative",
        transform: CSS.Translate.toString(transform),
        backgroundColor: styling.color,
    }

    return (
        <>
            <IconButton
                ref={setNodeRef}
                className={className}
                style={{...dragStyle, ...style}}
                {...listeners}
                {...attributes}
                onClick={() => setIsOpen(true)}
            >
                <Icon fontSize={"small"}/>
            </IconButton>
            <Dialog
                open={isOpen}
                onClose={() => setIsOpen(false)}
                maxWidth="md"
                fullWidth
            >
                <Card>
                    <CardHeader title={node.title} sx={{backgroundColor: styling.color}}/>
                    <CardContent className="w-full">
                        {node.description}
                    </CardContent>
                </Card>
            </Dialog>
        </>
    );
};

export default NodeDraggable;