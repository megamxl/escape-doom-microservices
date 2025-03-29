import React, {useState} from 'react';
import {AutoStories, Search, Settings, Visibility} from "@mui/icons-material";
import {Backdrop, Box, Card, CardContent, IconButton, Stack, Typography} from "@mui/material";
import {amber, blue, deepPurple, purple} from "@mui/material/colors";
import {NodeDTO, PositionDTO} from "@/app/gen/player";

//TODO: Refactor this class to adhere to DRY principle

interface IconButtonInt {
    pos: PositionDTO,
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
                top: `${pos.top_percentage}%`,
                left: `${pos.left_percentage}%`,
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
                                node_type,
                                node_info,
                                position
                            }: NodeDTO, /*codeSetter: React.Dispatch<React.SetStateAction<string>>*/) => {
    const [isOpen, setIsOpen] = useState(false)

    return (
        <>
            <IconButtonProp
                pos={position!}
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
                            {node_info?.title}
                        </Typography>
                    </Stack>
                    <CardContent>
                        <Typography color={"grey"}> Object Description </Typography>
                        <Typography mb={2}> {node_info?.description} </Typography>

                        {/*<Box sx={{backgroundColor: '#2c2c2c', p: 1, mb: 2}}>*/}
                        {/*    <Typography fontWeight={"bold"} fontSize={14} mb={1}> Return </Typography>*/}
                        {/*    <Typography> {node_info.returnType} </Typography>*/}
                        {/*    <Divider sx={{flexGrow: 1, borderBottomWidth: 2, my: 2}} orientation="horizontal"/>*/}
                        {/*    <Typography fontWeight={"bold"} fontSize={14} mb={1}> Non-real example </Typography>*/}
                        {/*    <Typography> {nodeInfos.exampleInput} </Typography>*/}
                        {/*</Box>*/}
                        {/*<Stack direction={"row"} justifyContent={"end"}>*/}
                        {/*    <Button sx={{backgroundColor: amber[600]}} variant="contained" onClick={() => {*/}
                        {/*        codeSetter(nodeInfos.codeSnipped)*/}
                        {/*    }}> Connect </Button>*/}
                        {/*</Stack>*/}
                    </CardContent>
                </Card>
            </Backdrop>
        </>
    )
}

export const StoryNode = ({node_type, node_info, position}: NodeDTO) => {
    const [isOpen, setIsOpen] = useState(false)

    const mainColor = purple[400];

    return (
        <>
            <IconButtonProp
                pos={position!}
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
                            {node_info?.title}
                        </Typography>
                    </Stack>
                    <CardContent>
                        <Typography mb={2}> {node_info?.description} </Typography>
                    </CardContent>
                </Card>
            </Backdrop>
        </>
    )
}

export const DetailsNode = ({node_info, node_type, position}: NodeDTO) => {
    const [isOpen, setIsOpen] = useState(false)

    const mainColor = blue[600]

    return (
        <>
            <IconButtonProp
                pos={position!}
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
                            {node_info?.title}
                        </Typography>
                    </Stack>
                    <CardContent>
                        <Stack direction="row" height="400px" gap={2}>
                            <Box width="80%" height="100%"
                                 sx={{
                                     backgroundImage: `url(${node_info?.imageURI})`,
                                     backgroundSize: "contain",
                                     backgroundRepeat: "no-repeat"
                                 }}/>
                            <Typography mb={2}> {node_info?.description} </Typography>
                        </Stack>
                    </CardContent>
                </Card>
            </Backdrop>
        </>
    )
}

export const ZoomNode = ({node_info, node_type, position}: NodeDTO) => {
    return (
        <IconButton
            size="small"
            onClick={() => {
                window.location.reload()
            }}
            sx={{
                position: "relative",
                left: position?.left_percentage,
                top: position?.top_percentage,
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

const renderNodeType = ({node_info, node_type, position}: NodeDTO) => {
    if (position === undefined || node_type === undefined || node_info === undefined) return;
    switch (node_type) {
        case "CONSOLE":
            return ConsoleNode({node_info, node_type, position})
        case "STORY":
            return StoryNode({node_info, node_type, position})
        case "DETAIL":
            return DetailsNode({node_info, node_type, position})
        case "ZOOM":
            return ZoomNode({node_info, node_type, position})
        default:
            console.error(`Invalid Node Type: ${node_type}`)
            return <></>
    }
}

const Node = ({node_info, node_type, position}: NodeDTO) => {
    return (
        renderNodeType({node_info, node_type, position})
    );
};

export default Node;