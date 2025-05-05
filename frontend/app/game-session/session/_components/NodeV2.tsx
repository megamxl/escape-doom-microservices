import React, {useState} from 'react';
import IconButton from "@mui/material/IconButton";
import {AutoStories, Search, Settings, SvgIconComponent, Visibility} from "@mui/icons-material";
import {Card, CardContent, CardHeader, CardMedia, Dialog, DialogContent, Stack, Typography} from "@mui/material";
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
}

const NodeV2 = ({node}: NodeV2Props) => {
    const {position, title, description, node_specifics} = node;
    const {icon: Icon, styling} = nodeTypeClassMapper[node_specifics?.node_type]
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
            <Dialog open={isOpen} onClose={() => setIsOpen(false)} maxWidth="lg" fullWidth>
                <DialogContent>
                    <Card className="h-[30vh]">
                        <CardHeader title={title} sx={{ backgroundColor: styling.color }}/>
                        <Stack direction="row" className="h-full">
                            <CardMedia
                                component="img"
                                image="https://images.squarespace-cdn.com/content/v1/607f89e638219e13eee71b1e/1684821560422-SD5V37BAG28BURTLIXUQ/michael-sum-LEpfefQf4rU-unsplash.jpg"
                                alt="Node Image"
                                sx={{width: "33.3%", objectFit: "cover"}}
                            />
                            <CardContent sx={{width: "100%"}}>
                                <Typography sx={{verticalAlign: "center"}} fontWeight={"bold"}>
                                    {description}
                                </Typography>
                            </CardContent>
                        </Stack>
                    </Card>
                </DialogContent>
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