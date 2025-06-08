import {CodeExecResponse} from "@/app/types/game-session/CodeExecResponse.ts";
import React, {useRef, useState} from 'react';
import {Avatar, FormControl, MenuItem, Select, SelectChangeEvent, Stack, Tooltip, Typography} from "@mui/material";
import EditorContainer from "@/app/game-session/session/_components/EditorContainer.tsx";
import {PlayArrow} from "@mui/icons-material";
import Editor from '@monaco-editor/react';
import {LoadingButton} from '@mui/lab';
import CodeExectuionDisplay from "@/app/game-session/session/_components/CodeExectuionDisplay.tsx";
import {
    CodingRiddleDTO,
    EscapeRoomResult,
    escapeRoomResultStatusEnum,
    EscapeRoomSolutionSubmition, useGetLevelResultHook, useSubmitSolutionAttemptForCurrentLevelHook
} from "@/app/gen/player";
import {GameSessionData} from "@/app/utils/game-session-handler.ts";
import {CompileStatus} from "@/app/enums/CompileStatus.ts";

type CodingRiddleProps = {
    riddlePassed: CodingRiddleDTO
    session: GameSessionData
}

type RiddleInfo ={
    riddle: CodingRiddleDTO,
    selectedLanguage: string,
    code: string,
    codeExecutionResponse: EscapeRoomResult,
    sessionData: GameSessionData,
    loadingExecResponse: boolean
}

const CodingRiddle = ({riddlePassed, session}: CodingRiddleProps) => {

    const [riddleInfo, setRiddleInfo] = useState<RiddleInfo>({
        riddle: riddlePassed,
        selectedLanguage: Object.keys(riddlePassed.code)[0],
        code: riddlePassed.code[Object.keys(riddlePassed.code)[0]],
        sessionData: session,
        codeExecutionResponse: {
            status: CompileStatus.COMPILED,
            output: "",
        },
        loadingExecResponse: false
    })

    const monacoEditorRef = useRef()


    const handleLanguageChange =  (event: SelectChangeEvent) => {
        setRiddleInfo(prev => ({...prev,
            selectedLanguage: event.target.value,
            code: riddleInfo.riddle.code[event.target.value]
        }))
    }

    const handleEditorMount = (editor: any) => {
        monacoEditorRef.current = editor
    }

    const handleCodeChange = (value: string) => {
        setRiddleInfo(prev => ({...prev,
            code: value
        }))
    }

    const useSubmitSolution = useSubmitSolutionAttemptForCurrentLevelHook();

    const {
        data: result,
        refetch: refetchResult
    } = useGetLevelResultHook({player_session_id: riddleInfo.sessionData.sessionID}, {query: {enabled: false}});

    const sleep = (ms: number) => new Promise(r => setTimeout(r, ms));

    const handleCodeSubmission = async () => {
        setRiddleInfo(prev => ({...prev,
            loadingExecResponse: true
        }))

        const body: EscapeRoomSolutionSubmition = {
            solution: riddleInfo.code,
            //@ts-ignore
            language: riddleInfo.selectedLanguage.toUpperCase(),
        }

        useSubmitSolution.mutate({
            player_session_id: riddleInfo.sessionData.sessionID,
            data: body
        })


        while (true) {
            console.log("Waiting for code compilation completed")
            await sleep(1000);
            const refetchData = await refetchResult();


            if (refetchData.data !== undefined && refetchData.data.status !== escapeRoomResultStatusEnum.WAITING) {

                if (refetchData.data.status === CompileStatus.WON) {
                    //setShowWinPopup(true)
                    console.log("escapeRoom won")
                    return
                }

                console.log("Code compilation completed")
                setRiddleInfo(prev => ({...prev,
                    codeExecutionResponse: refetchData.data,
                    loadingExecResponse: false
                }))

                break
            }
        }

    }

    return (
        <Stack direction="column" height="100vh" maxWidth={"31.5vw"}>
            <EditorContainer>
                <Stack direction="row" alignItems="center">
                    <Typography mx={2}> Code </Typography>
                    <FormControl variant="standard" size='small'>
                        <Select
                            labelId='languageSelect'
                            value={riddleInfo.selectedLanguage}
                            label="Language"
                            onChange={handleLanguageChange}
                            variant={"standard"}>
                            {
                                Object.keys(riddleInfo.riddle.code).map(language => {
                                    return (
                                        <MenuItem key={language}
                                                  value={language}> {language[0]}{language.slice(1).toLowerCase()} </MenuItem>
                                    )
                                })
                            }
                        </Select>
                    </FormControl>

                    <Tooltip title={riddleInfo.sessionData.playerName.slice(0, 1).toUpperCase()} arrow>
                        <Avatar sx={{ml: "auto"}}>{riddleInfo.sessionData.playerName}</Avatar>
                    </Tooltip>
                </Stack>
            </EditorContainer>
            <EditorContainer sx={{flexGrow: 1, flexShrink: 1}}>
                <Editor
                    height="100%"
                    width="30vw"
                    language={riddleInfo.selectedLanguage.toLowerCase()}
                    value={riddleInfo.code}
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
                        loading={riddleInfo.loadingExecResponse}
                        loadingPosition="start"
                        onClick={handleCodeSubmission}
                    >
                        <span> Execute </span>
                    </LoadingButton>
                </Stack>
            </EditorContainer>
            <CodeExectuionDisplay codeExecResponse={riddleInfo.codeExecutionResponse}/>
        </Stack>
    )
};

export default CodingRiddle;