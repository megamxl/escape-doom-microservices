import React, {useState} from 'react';
import {NodeDTO} from "@/app/gen/player";
import IconButton from "@mui/material/IconButton";
import {nodeTypeClassMapper} from "@/app/game-session/session/_components/nodes/Node.tsx";
import {Card, CardContent, CardHeader, Dialog} from "@mui/material";

type EditorNodeProps = {
    node: NodeDTO
}

const EditorNode = ({node}: EditorNodeProps) => {
    const [isOpen, setIsOpen] = useState(false)
    const {title, node_specifics, position} = node

    if (!node_specifics?.node_type) return;

    const {icon: Icon, styling} = nodeTypeClassMapper[node_specifics?.node_type]
    return (
        <>
            <IconButton sx={{
                position: "absolute",
                top: `${position?.top_percentage}%`,
                left: `${position?.left_percentage}%`
            }}>
                <Icon fontSize={"small"}/>
            </IconButton>
            <Dialog open={isOpen} onClose={() => setIsOpen(false)} maxWidth="md" fullWidth>
                <Card>
                    <CardHeader title={title} sx={{backgroundColor: styling.color}}/>
                    <CardContent className="w-full">
                    </CardContent>
                </Card>
            </Dialog>
        </>
    );
};

export default EditorNode;