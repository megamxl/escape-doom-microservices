'use client'

import React, {CSSProperties, useState} from 'react';
import {NodeDTO} from "@/app/gen/player";
import {useDraggable} from "@dnd-kit/core";
import IconButton from "@mui/material/IconButton";
import {nodeTypeClassMapper} from "@/app/game-session/session/_components/nodes/Node.tsx";
import {CSS} from '@dnd-kit/utilities';
import {Card, CardContent, CardHeader, Dialog} from "@mui/material";
import BasicNodeForm from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/BasicNodeForm.tsx";
import ZoomNodeForm from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/ZoomNodeForm.tsx";
import {toCapitalCase} from "@/app/utils/stringFormatting.ts";

type DnDNodeProps = {
    node: NodeDTO
    onDeletion: (nodeId: string) => void
    className?: string;
    style?: React.CSSProperties
}

const NodeDraggable = ({node, onDeletion, className, style}: DnDNodeProps) => {
    const {
        attributes,
        listeners,
        setNodeRef,
        transform,
        isDragging
    } = useDraggable({
        id: node.node_id!,
        data: {
            node: node
        },
    })
    const [isOpen, setIsOpen] = useState(false)
    const [nodeState, setNodeState] = useState(node)

    if (!node || !node.node_specifics) return null

    const {icon: Icon, styling} = nodeTypeClassMapper[node.node_specifics.node_type!]
    const dragStyle: CSSProperties = {
        position: "relative",
        transform: CSS.Translate.toString(transform),
        backgroundColor: styling.color,
        opacity: isDragging ? 0.5 : 1,
        cursor: isDragging ? 'grabbing' : 'grab',
        zIndex: isDragging ? 1000 : 'auto',
        ...style
    }

    const formatTitle = () => `${toCapitalCase(node.node_specifics?.node_type ?? '')} Node`

    return (
        <>
            <IconButton
                ref={setNodeRef}
                className={className}
                style={dragStyle}
                {...listeners}
                {...attributes}
                onClick={() => {
                    if (!isDragging) setIsOpen(true)
                }}
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
                    <CardHeader title={formatTitle()} sx={{backgroundColor: styling.color, height: '4rem'}}/>
                    <CardContent className="w-full">
                        {(node.node_specifics.node_type === "CONSOLE" || node.node_specifics.node_type === "DETAIL") && <>
                            <BasicNodeForm node={nodeState} setNode={setNodeState} onDeletion={onDeletion} />
                        </>}
                        {node.node_specifics.node_type === "ZOOM" &&
                            <ZoomNodeForm onDeletion={onDeletion} node={nodeState} setNode={setNodeState}/>
                        }
                    </CardContent>
                </Card>
            </Dialog>
        </>
    );
};

export default NodeDraggable;