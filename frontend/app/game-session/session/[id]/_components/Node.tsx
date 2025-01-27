import React, {useState} from 'react';
import {AutoStories, Search, Settings, Visibility} from "@mui/icons-material";
import {Backdrop, Box, Button, Card, CardContent, Divider, IconButton, Stack, Typography} from "@mui/material";
import {amber, blue, deepPurple, purple} from "@mui/material/colors";
import {NodeInstance, NodeState} from "@/app/types/game-session/NodeState";
import {NodeType} from "@/app/types/game-session/NodeType";

interface IconButtonInt {
    pos: { x: number, y: number },
    color: string,
    icon: any,
    openfunction: React.Dispatch<React.SetStateAction<boolean>>
}

const iconSize = 30
const maxWidthConst = "40vw"
const minWidthConst = "600px"

const IconButtonProp: React.FC<IconButtonInt> = ({pos, color, icon, openfunction}: IconButtonInt) => {
    return (
        <IconButton
            size="small"
            onClick={() => openfunction(true)}
            sx={{
                color: color,
                position: "absolute",
                top: `${pos.y * 100}%`,
                left: `${pos.x * 100}%`,
                width: iconSize,
                height: iconSize,
                border: 2,
                borderRadius: '50%'
            }}>
            {icon}
        </IconButton>
    )
}

export const ConsoleNode = ({
                                pos,
                                nodeInfos
                            }: NodeInstance, codeSetter: React.Dispatch<React.SetStateAction<string>>) => {
    const [isOpen, setIsOpen] = useState(false)
    return (
        <>
            <IconButtonProp
                pos={pos}
                color={amber[600]}
                icon={<Settings fontSize='small'/>}
                openfunction={setIsOpen}
            />
            <Backdrop sx={{zIndex: (theme) => theme.zIndex.drawer + 1}} open={isOpen} onClick={() => setIsOpen(false)}>
                <Card sx={{minWidth: minWidthConst, maxWidth: maxWidthConst}}>
                    <Stack
                        direction="row"
                        alignItems={"center"}
                        sx={{backgroundColor: amber[600]}}
                        minHeight={50}
                        pl={2}
                    >
                        <Typography
                            sx={{verticalAlign: "center"}}
                            color={"black"}
                            fontWeight={"bold"}
                        >
                            {nodeInfos.title}
                        </Typography>
                    </Stack>
                    <CardContent>
                        <Typography color={"grey"}> Object Description </Typography>
                        <Typography mb={2}> {nodeInfos.desc} </Typography>

                        <Box sx={{backgroundColor: '#2c2c2c', p: 1, mb: 2}}>
                            <Typography fontWeight={"bold"} fontSize={14} mb={1}> Return </Typography>
                            <Typography> {nodeInfos.returnType} </Typography>
                            <Divider sx={{flexGrow: 1, borderBottomWidth: 2, my: 2}} orientation="horizontal"/>
                            <Typography fontWeight={"bold"} fontSize={14} mb={1}> Non-real example </Typography>
                            <Typography> {nodeInfos.exampleInput} </Typography>
                        </Box>
                        <Stack direction={"row"} justifyContent={"end"}>
                            <Button sx={{backgroundColor: amber[600]}} variant="contained" onClick={() => {
                                codeSetter(nodeInfos.codeSnipped)
                            }}> Connect </Button>
                        </Stack>
                    </CardContent>
                </Card>
            </Backdrop>
        </>
    )
}

export const StoryNode = ({pos, nodeInfos}: NodeInstance) => {
    const [isOpen, setIsOpen] = useState(false)

    const mainColor = purple[400];

    return (
        <>
            <IconButtonProp
                pos={pos}
                color={mainColor}
                icon={<AutoStories fontSize="small"/>}
                openfunction={setIsOpen}
            />
            <Backdrop sx={{zIndex: (theme) => theme.zIndex.drawer + 1}} open={isOpen} onClick={() => setIsOpen(false)}>
                <Card sx={{minWidth: minWidthConst, maxWidth: maxWidthConst}}>
                    <Stack
                        direction="row"
                        alignItems={"center"}
                        sx={{backgroundColor: mainColor}}
                        minHeight={50}
                        pl={2}
                    >
                        <Typography
                            sx={{verticalAlign: "center"}}
                            color={"black"}
                            fontWeight={"bold"}
                        >
                            {nodeInfos.title}
                        </Typography>
                    </Stack>
                    <CardContent>
                        <Typography mb={2}> {nodeInfos.desc} </Typography>
                    </CardContent>
                </Card>
            </Backdrop>
        </>
    )
}

export const DetailsNode = ({pos, nodeInfos}: NodeInstance) => {
    const [isOpen, setIsOpen] = useState(false)

    const mainColor = blue[600]

    return (
        <>
            <IconButtonProp
                pos={pos}
                color={mainColor}
                icon={<Search fontSize='small'/>}
                openfunction={setIsOpen}
            />
            <Backdrop sx={{zIndex: (theme) => theme.zIndex.drawer + 1}} open={isOpen} onClick={() => setIsOpen(false)}>
                <Card sx={{minWidth: minWidthConst, maxWidth: maxWidthConst}}>
                    <Stack
                        direction="row"
                        alignItems={"center"}
                        sx={{backgroundColor: mainColor}}
                        minHeight={50}
                        pl={2}
                    >
                        <Typography
                            sx={{verticalAlign: "center"}}
                            color={"black"}
                            fontWeight={"bold"}
                        >
                            {nodeInfos.title}
                        </Typography>
                    </Stack>
                    <CardContent>
                        <Stack direction="row" height="400px" gap={2}>
                            <Box width="80%" height="100%"
                                 sx={{
                                     backgroundImage: `url(${nodeInfos.png})`,
                                     backgroundSize: "contain",
                                     backgroundRepeat: "no-repeat"
                                 }}/>
                            <Typography mb={2}> {nodeInfos.desc} </Typography>
                        </Stack>
                    </CardContent>
                </Card>
            </Backdrop>
        </>
    )
}

export const ZoomNode = ({pos, nodeInfos}: NodeInstance) => {
    return (
        <IconButton
            size="small"
            onClick={() => {
                window.location.reload()
            }}
            sx={{
                position: "relative",
                left: pos.x,
                top: pos.y,
                color: deepPurple[400],
                width: iconSize,
                height: iconSize,
                border: 2,
                borderRadius: '50%'
            }}>
            <Visibility fontSize='small'/>
        </IconButton>
    )
}

const renderNodeType = ({type, pos, nodeInfos, codeSetter}: NodeState) => {
    switch (type) {
        case NodeType.CONSOLE:
            return ConsoleNode({pos, nodeInfos}, codeSetter)
        case NodeType.DETAILS:
            return StoryNode({pos, nodeInfos})
        case NodeType.STORY:
            return DetailsNode({pos, nodeInfos})
        case NodeType.ZOOM:
            return ZoomNode({pos, nodeInfos})
        default:
            console.error(`Invalid Node Type: ${type}`)
            return <></>
    }
}

const Node = ({type, pos, nodeInfos, codeSetter}: NodeState) => {
    return (
        renderNodeType({type, pos, nodeInfos, codeSetter})
    );
};

export default Node;