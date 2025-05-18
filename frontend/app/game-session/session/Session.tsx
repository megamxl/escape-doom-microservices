'use client'

import React, {useEffect, useRef, useState} from 'react';
import {StageState} from "@/app/types/game-session/StageState.ts";
import {CodeLanguage} from "@/app/enums/CodeLanguage.ts";
import {Avatar, CircularProgress, FormControl, MenuItem, Select, Stack, Tooltip, Typography} from "@mui/material";
import EditorContainer from "@/app/game-session/session/_components/EditorContainer.tsx";
import {PlayArrow} from "@mui/icons-material";
import Editor from '@monaco-editor/react';
import {LoadingButton} from '@mui/lab';
import {CompileStatus} from "@/app/enums/CompileStatus.ts";
import {redirect} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths.ts";
import CodeExectuionDisplay from "@/app/game-session/session/_components/CodeExectuionDisplay.tsx";
import {
    EscapeRoomResult,
    escapeRoomResultStatusEnum,
    EscapeRoomSolutionSubmition,
    SceneDTO,
    useGetLevelOfSessionByPlayerSessionIDHook,
    useGetLevelResultHook,
    useSubmitSolutionAttemptForCurrentLevelHook
} from "@/app/gen/player";
import Node from "@/app/game-session/session/_components/nodes/Node.tsx";
import {getSessionStorageItem} from "@/app/utils/session-storage-handler.ts";
import {player_name_key, session_id_key} from "@/app/utils/Constants.ts";
import ErrorDisplayCard, {ErrorDetails} from "@/app/game-session/session/_components/ErrorDisplayCard.tsx";

const Session = () => {
    const [currentScene, setCurrentScene] = useState<SceneDTO>()
    const [sessionID, setSessionID] = useState("")
    const [playerName, setPlayerName] = useState({
        short: "",
        long: ""
    })
    const [stageState, setStageState] = useState<StageState>({
        language: CodeLanguage.JAVA,
        stageScene: undefined
    })

    const [loading, setLoading] = useState(false)
    const [code, setCode] = useState<string>("Initial code");
    const [codeExecutionResponse, setCodeExecutionResponse] = useState<EscapeRoomResult>({
        status: CompileStatus.COMPILED,
        output: "",
    })

    useEffect(() => {

        const sessionStorageItem = getSessionStorageItem(session_id_key);
        const playername = getSessionStorageItem(player_name_key);

        if (sessionStorageItem !== null) {
            setSessionID(sessionStorageItem)
        }

        if (playername !== null && playername !== "") {
            //TODO check empty player name or short name
            setPlayerName(
                {short: playername.slice(0, 1).toUpperCase(), long: playername}
            )
            return
        }

        redirect(GAME_SESSION_APP_PATHS.STUDENT_JOIN)

    }, [])

    /* TanStack Query Calls */
    const {
        data: stageInformation,
        isFetching: isFetchingStageInformation,
        isError: errorState,
        error: errorObject
    } = useGetLevelOfSessionByPlayerSessionIDHook({player_session_id: sessionID});

    const {
        data: result,
        refetch: refetchResult
    } = useGetLevelResultHook({player_session_id: sessionID}, {query: {enabled: false}});

    const useSubmitSolution = useSubmitSolutionAttemptForCurrentLevelHook();

    useEffect(() => {

        const sessionStorageItem = getSessionStorageItem(session_id_key);

        if (sessionStorageItem !== null) {
            setSessionID(sessionStorageItem)
            return
        }

        redirect(GAME_SESSION_APP_PATHS.STUDENT_JOIN)

    }, [])

    useEffect(() => {
        if (stageInformation?.riddle?.function === undefined || stageInformation.scenes === undefined) return;

        setCode(stageInformation?.riddle?.function)
        console.log("Received: ", stageInformation)
        setCurrentScene(stageInformation.scenes.find(s => s.scene_sequence == 1))
    }, [stageInformation])

    const handleZoomChange = (targetSceneId: string) => {
        if (stageInformation?.scenes === undefined) return;
        const newIdx = stageInformation?.scenes.findIndex(scene => scene.scene_id == targetSceneId) ?? -1

        if (newIdx !== -1) {
            console.log("New scene: ", stageInformation?.scenes[newIdx])
            setCurrentScene(stageInformation?.scenes[newIdx])
        } else {
            console.warn(`Scene with ID ${targetSceneId} not found`)
        }
    }

    const monacoEditorRef = useRef()

    const sleep = (ms: number) => new Promise(r => setTimeout(r, ms));

    const handleCodeSubmission = async () => {
        setLoading(true)

        const body: EscapeRoomSolutionSubmition = {
            solution: code,
            language: stageState.language,
        }

        useSubmitSolution.mutate({
            player_session_id: sessionID,
            data: body
        })

        console.log(result)

        while (true) {
            console.log("Waiting for code compilation completed")
            await sleep(1000);
            const refetchData = await refetchResult();

            console.log(refetchData.data)


            if (refetchData.data !== undefined && refetchData.data.status !== escapeRoomResultStatusEnum.WAITING) {
                console.log("Code compilation completed")
                setCodeExecutionResponse(refetchData.data)
                setLoading(false)
                break
            }
        }

        console.log(result)

        if (codeExecutionResponse.status === CompileStatus.WON) {
            console.log("escapeRoom won")
            //removeGameSession()
            //redirect(`${GAME_SESSION_APP_PATHS.LEADERBOARD}/${roomPinOfSession}`)
        }

    }

//const getCodeResult = async (): Promise<CodeExecResponse | undefined> => {

    // const response = await refetchCodeResult();
    // return response.data;
//}

    const handleLanguageChange = () => {
    }

    const handleCodeChange = (value: string) => {
        setCode(value)
    }

    const handleEditorMount = (editor: any) => {
        monacoEditorRef.current = editor
    }

// Error and loading display before returning the content
    if (isFetchingStageInformation) {
        return <LoadingDisplay/>;
    }


    if (errorState) {
        const errorDetails: ErrorDetails = errorObject?.response?.data || {
            timestamp: new Date().toISOString(),
            status: 500,
            error: "Internal Server Error",
            message: "Something went wrong",
            path: "unknown",
        };

        return (
            <ErrorDisplayCard
                errorDetails={errorDetails}
                onRetry={() => console.log("retry")}
                onBack={() => console.log("To Join")}
            />
        );

    }

    return (

        <Stack direction="row" alignItems="center" height="100vh">
            <Stack direction="column" height="100vh" maxWidth={"31.5vw"}>
                <EditorContainer>
                    <Stack direction="row" alignItems="center">
                        <Typography mx={2}> Code </Typography>
                        <FormControl variant="standard" size='small'>
                            <Select
                                labelId='languageSelect'
                                value={stageState.language}
                                label="Language"
                                onChange={handleLanguageChange}
                                variant={"standard"}>
                                {
                                    Object.keys(CodeLanguage).map(language => {
                                        return (
                                            <MenuItem key={language}
                                                      value={language}> {language[0]}{language.slice(1).toLowerCase()} </MenuItem>
                                        )
                                    })
                                }
                            </Select>
                        </FormControl>

                        <Tooltip title={playerName.long} arrow>
                            <Avatar sx={{ml: "auto"}}>{playerName.short}</Avatar>
                        </Tooltip>
                    </Stack>
                </EditorContainer>
                <EditorContainer sx={{flexGrow: 1, flexShrink: 1}}>
                    <Editor
                        height="100%"
                        width="30vw"
                        language={stageState.language.toLowerCase()}
                        value={code}
                        onMount={handleEditorMount}
                        onChange={handleCodeChange}
                        theme={"vs-dark"}
                        options={{
                            wordWrap: 'on',
                            minimap: {enabled: false},
                            folding: false,
                            lineNumbersMinChars: 3,
                            scrollBeyondLastLine: false,
                            automaticLayout: true,
                        }}
                    />
                </EditorContainer>
                <EditorContainer>
                    <Stack direction="column">
                        <Typography position={{sx: 'relative', lg: 'absolute'}}> Actions </Typography>
                        <LoadingButton
                            sx={{
                                height: 60,
                                width: 250,
                                m: 1,
                                alignSelf: 'center'
                            }}
                            startIcon={<PlayArrow/>}
                            variant='contained'
                            loading={loading}
                            loadingPosition="start"
                            onClick={handleCodeSubmission}
                        >
                            <span> Execute </span>
                        </LoadingButton>
                    </Stack>
                </EditorContainer>
                <CodeExectuionDisplay codeExecResponse={codeExecutionResponse}/>
            </Stack>

            <div className="relative w-full mx-auto">
                <img
                    //@ts-ignore
                    src={`${currentScene?.background_image_uri}`}
                    alt="Background"
                    className="w-full bg-no-repeat bg-contain"
                />
                {
                    //@ts-ignore
                    currentScene?.nodes.map((node) => {
                        return (
                            <Node key={node.node_id} node={node} onZoomChangeScene={handleZoomChange}/>
                        )
                    })
                }
            </div>
        </Stack>
    );
};

const LoadingDisplay = () => {
    return (
        <Stack height={"100vh"} width={"100vw"} direction={"column"} justifyContent={"center"} gap={4}
               alignItems={"center"}>
            <CircularProgress size={"10rem"} variant={"indeterminate"}/>
            <Typography variant={"h3"}> Loading... </Typography> </Stack>
    )
}

export default Session;