import React, {ReactNode, useState} from 'react';
import IconButton from "@mui/material/IconButton";
import {AutoStories, Search, Settings, SvgIconComponent, Visibility} from "@mui/icons-material";
import {Card, CardContent, CardHeader, Dialog} from "@mui/material";
import {amber, blue, purple, teal} from "@mui/material/colors";
import {NodeDTO, NodeType} from "@/app/gen/player";
import ZoomNode from "@/app/game-session/session/_components/nodes/ZoomNode.tsx";
import ConsoleNodeDetails from "@/app/game-session/session/_components/nodes/ConsoleNodeDetails.tsx";
import DetailNodeDetails from "@/app/game-session/session/_components/nodes/DetailNodeDetails.tsx";

type NodeTypeConfig = {
    styling: {
        color: string
    },
    icon: SvgIconComponent
}

type NodeV2Props = {
    node: NodeDTO
    children?: ReactNode
}

const Node = ({node}: NodeV2Props) => {
    if (node.node_specifics?.node_type === undefined) return;

    if (node.node_specifics.node_type === "ZOOM") return <ZoomNode node={node}/>

    return (
        <BaseNode node={node}>
            { renderSpecifics(node) }
        </BaseNode>
    );
};

const BaseNode = ({node, children}: NodeV2Props) => {
    const [isOpen, setIsOpen] = useState(false)
    const {position, title, node_specifics} = node

    if (node_specifics?.node_type === undefined) return;
    const {icon: Icon, styling} = nodeTypeClassMapper[node_specifics?.node_type]

    return (
        <>
            <IconButton
                onClick={() => setIsOpen(true)}
                style={{
                    position: "absolute",
                    top: `${position?.top_percentage}%`,
                    left: `${position?.left_percentage}%`,
                    color: "#fff",
                    backgroundColor: styling.color,
                    borderRadius: "50%",
                    width: "clamp(1.5rem, 2vw, 3rem)",
                    height: "clamp(1.5rem, 2vw, 3rem)"
                }}>
                <Icon fontSize={"small"}/>
            </IconButton>
            <Dialog open={isOpen} onClose={() => setIsOpen(false)} maxWidth="md" fullWidth>
                <Card>
                    <CardHeader title={title} sx={{backgroundColor: styling.color}}/>
                    <CardContent className="w-full">
                        {children}
                    </CardContent>
                </Card>
            </Dialog>
        </>
    );
}

const renderSpecifics = (node: NodeDTO): ReactNode => {
    if (node.node_specifics === undefined || node.description === undefined) return;
    switch (node.node_specifics.node_type) {
        case "CONSOLE":
            return <ConsoleNodeDetails specifics={node.node_specifics} desc={node.description}/>
        case "DETAIL":
            return <DetailNodeDetails specifics={node.node_specifics} desc={node.description} />
    }
}

const nodeTypeClassMapper: Record<NodeType, NodeTypeConfig> = {
    STORY: {
        styling: {
            color: teal[500]
        },
        icon: AutoStories,
    },
    DETAIL: {
        styling: {
            color: blue[600]
        },
        icon: Search,
    },
    CONSOLE: {
        styling: {
            color: amber[600]
        },
        icon: Settings,
    },
    ZOOM: {
        styling: {
            color: purple[400]
        },
        icon: Visibility,
    }
}

export default Node;