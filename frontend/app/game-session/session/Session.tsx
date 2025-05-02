'use client'

import React, {useEffect, useRef, useState} from 'react';
import {StageState} from "@/app/types/game-session/StageState.ts";
import {CodeExecResponse} from "@/app/types/game-session/CodeExecResponse.ts";
import {SubmittedCodeBody} from "@/app/types/game-session/SubmittedCodeBody.ts";
import {CodeLanguage} from "@/app/enums/CodeLanguage.ts";
import {CircularProgress, FormControl, MenuItem, Select, Stack, Typography} from "@mui/material";
import EditorContainer from "@/app/game-session/session/_components/EditorContainer.tsx";
import {PlayArrow} from "@mui/icons-material";
import Editor from '@monaco-editor/react';
import {LoadingButton} from '@mui/lab';
import {CompileStatus} from "@/app/enums/CompileStatus.ts";
import {redirect} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths.ts";
import CodeExectuionDisplay from "@/app/game-session/session/_components/CodeExectuionDisplay.tsx";
import {useGetLevelOfSessionByPlayerSessionIDHook} from "@/app/gen/player";
import NodeV2 from "@/app/game-session/session/_components/NodeV2.tsx";
import {getSessionStorageItem} from "@/app/utils/session-storage-handler.ts";
import {session_id_key} from "@/app/utils/Constants.ts";
import ErrorDisplayCard, {ErrorDetails} from "@/app/game-session/session/_components/ErrorDisplayCard.tsx";

const Session = () => {

    const [sessionID, setSessionID] = useState("")

    useEffect(() => {

        const sessionStorageItem = getSessionStorageItem(session_id_key);

        if (sessionStorageItem !== null) {
            setSessionID(sessionStorageItem)
            return
        }

        redirect(GAME_SESSION_APP_PATHS.STUDENT_JOIN)

    }, [])

    const [stageState, setStageState] = useState<StageState>({
        language: CodeLanguage.JAVA,
        stageScene: undefined
    })

    const [loading, setLoading] = useState(false)
    const [code, setCode] = useState<string>("Initial code");
    const [codeExecutionResponse, setCodeExecutionResponse] = useState<CodeExecResponse>({
        status: CompileStatus.COMPILED,
        output: ''
    })
    const [submittedCodeBody, setSubmittedCodeBody] = useState<SubmittedCodeBody>({
        playerSessionId: sessionID,
        language: stageState.language,
        code: code,
        codeRiddleID: 0,
        dateTime: new Date(Date.now())
    })

    /* TanStack Query Calls */
    const {
        data: stageInformation,
        isFetching: isFetchingStageInformation,
        isError: errorState,
        error: errorObject
    } = useGetLevelOfSessionByPlayerSessionIDHook({player_session_id: sessionID});


    const monacoEditorRef = useRef()

    const sleep = (ms: number) => new Promise(r => setTimeout(r, ms));

    const handleCodeSubmission = async () => {
        setLoading(true)
        // await reSubmitCode();
        let response = undefined
        /* while (true) {
             console.log("Waiting for code compilation completed")
             await sleep(250);
             //response = await getCodeResult();
             if (response?.status !== CompileStatus.WAITING && response !== undefined) {
                 setCodeExecutionResponse(response)
                 break
             }
         }

         // console.log("Compilation done", codeResultData)
         //
         // if (response?.status === CompileStatus.WON) {
         //     removeGameSession()
         //     redirect(`${GAME_SESSION_APP_PATHS.LEADERBOARD}/${roomPinOfSession}`)
         //
         // }
         setLoading(false)

         */
    }

//const getCodeResult = async (): Promise<CodeExecResponse | undefined> => {

    // const response = await refetchCodeResult();
    // return response.data;
//}

    const handleLanguageChange = () => {

    }

    const handleCodeChange = (value: any) => {
        setCode(value)
        setSubmittedCodeBody({
            "playerSessionId": sessionID,
            "language": stageState.language,
            "code": value,
            "codeRiddleID": 1,
            "dateTime": new Date(Date.now())
        })
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
                    src={`${stageInformation?.scenes[0].background_image_uri}`}
                    alt="Background"
                    className="w-full bg-no-repeat bg-contain"
                />
                {
                    //@ts-ignore
                    stageInformation?.scenes[0]?.nodes.map((node) => {
                        return (
                            <NodeV2 key={node.node_id} node={node} codeSetter={setCode}/>
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