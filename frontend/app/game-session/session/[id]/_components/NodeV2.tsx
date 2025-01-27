import React, {useState} from 'react';
import {NodeType} from "@/app/types/game-session/NodeType";
import IconButton from "@mui/material/IconButton";
import {AutoStories, Search, Settings, SvgIconComponent, Visibility} from "@mui/icons-material";
import {Card, CardContent, Dialog, Stack, Typography} from "@mui/material";
import {amber, blue, purple, teal} from "@mui/material/colors";
import {NodeInfo} from "@/app/types/game-session/StageNode";

export type NodeV2Props = {
    type: NodeType,
    position: { top: string, left: string },
    nodeInfos: NodeInfo
}

type NodeTypeConfig = {
    styling: {
        color: string
    },
    icon: SvgIconComponent
}

const NodeV2 = ({type, position, nodeInfos}: NodeV2Props) => {
    const {icon: Icon, styling} = nodeTypeClassMapper[type]
    const [isOpen, setIsOpen] = useState(false)

    return (
        <>
            <IconButton
                onClick={() => setIsOpen(true)}
                style={{
                    position: "absolute",
                    top: position.top,
                    left: position.left,
                    color: "#fff",
                    backgroundColor: styling.color,
                    borderRadius: "50%",
                    width: "clamp(1.5rem, 2vw, 3rem)",
                    height: "clamp(1.5rem, 2vw, 3rem)"
                }}>
                <Icon fontSize={"small"}/>
            </IconButton>
            <Dialog open={isOpen} onClose={() => setIsOpen(false)}>
                <Card sx={{minWidth: "400px", maxWidth: "60vw"}}>
                    <CardContent>
                        <Stack>
                            <Typography sx={{verticalAlign: "center"}} fontWeight={"bold"}>
                                {nodeInfos.title}
                            </Typography>
                        </Stack>
                        <Typography mb={2}> {nodeInfos.desc} </Typography>
                    </CardContent>
                </Card>
            </Dialog>
        </>
    );
};

const nodeTypeClassMapper: Record<NodeType, NodeTypeConfig> = {
    [NodeType.STORY]: {
        styling: {
            color: purple[400]
        },
        icon: AutoStories,
    },
    [NodeType.DETAILS]: {
        styling: {
            color: blue[600]
        },
        icon: Search,
    },
    [NodeType.CONSOLE]: {
        styling: {
            color: amber[600]
        },
        icon: Settings,
    },
    [NodeType.ZOOM]: {
        styling: {
            color: teal[500]
        },
        icon: Visibility,
    }
}

export default NodeV2;