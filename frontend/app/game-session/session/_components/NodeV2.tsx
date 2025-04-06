import React, {useState} from 'react';
import IconButton from "@mui/material/IconButton";
import {AutoStories, Search, Settings, SvgIconComponent, Visibility} from "@mui/icons-material";
import {Card, CardContent, Dialog, Stack, Typography} from "@mui/material";
import {amber, blue, purple, teal} from "@mui/material/colors";
import {NodeDTO, NodeType} from "@/app/gen/player";

type NodeTypeConfig = {
    styling: {
        color: string
    },
    icon: SvgIconComponent
}

type NodeV2Props = {
    node: NodeDTO
    codeSetter: React.Dispatch<React.SetStateAction<string>>
}

const NodeV2 = ({node, codeSetter}: NodeV2Props) => {
    const { node_info, node_type, position } = node;
    const {icon: Icon, styling} = nodeTypeClassMapper[node_type!]
    const [isOpen, setIsOpen] = useState(false)

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
            <Dialog open={isOpen} onClose={() => setIsOpen(false)}>
                <Card sx={{minWidth: "400px", maxWidth: "60vw"}}>
                    <CardContent>
                        <Stack>
                            <Typography sx={{verticalAlign: "center"}} fontWeight={"bold"}>
                                {node_info?.title}
                            </Typography>
                        </Stack>
                        <Typography mb={2}> {node_info?.description} </Typography>
                    </CardContent>
                </Card>
            </Dialog>
        </>
    );
};

const nodeTypeClassMapper: Record<NodeType, NodeTypeConfig> = {
    STORY: {
        styling: {
            color: purple[400]
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
            color: teal[500]
        },
        icon: Visibility,
    }
}

export default NodeV2;