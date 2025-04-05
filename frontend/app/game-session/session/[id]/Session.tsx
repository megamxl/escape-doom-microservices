'use client'

import React, {useRef, useState} from 'react';
import {StageState} from "@/app/types/game-session/StageState";
import {CodeExecResponse} from "@/app/types/game-session/CodeExecResponse";
import {SubmittedCodeBody} from "@/app/types/game-session/SubmittedCodeBody";
import {CodeLanguage} from "@/app/enums/CodeLanguage";
import {CircularProgress, FormControl, MenuItem, Select, Stack, Typography} from "@mui/material";
import EditorContainer from "@/app/game-session/session/[id]/_components/EditorContainer";
import {PlayArrow} from "@mui/icons-material";
import Editor from '@monaco-editor/react';
import {LoadingButton} from '@mui/lab';
import {CompileStatus} from "@/app/enums/CompileStatus";
import CodeExectuionDisplay from "@/app/game-session/session/[id]/_components/CodeExectuionDisplay";
import {useGetLevelOfSessionByPlayerSessionIDHook} from "@/app/gen/player";
import NodeV2 from "@/app/game-session/session/[id]/_components/NodeV2.tsx";

const Session = ({sessionID}: { sessionID: string }) => {

    // TODO: Thommy - Mit Backend Team absprechen das wir die neue Struktur bekommen, dann hauts auch hin
    // const nodes: NodeV2Props[] = [
    //     { type: NodeType.STORY, position: { top: "20%", left: "30%" }, nodeInfos: { desc: "Story Node", title: "Story" } },
    //     { type: NodeType.CONSOLE, position: { top: "50%", left: "60%" }, nodeInfos: { desc: "Story Node", title: "Story" } },
    //     { type: NodeType.DETAILS, position: { top: "70%", left: "40%" }, nodeInfos: { desc: "Story Node", title: "Story" } },
    //     { type: NodeType.ZOOM, position: { top: "50%", left: "40%" }, nodeInfos: { desc: "Story Node", title: "Story" } },
    // ];

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
    //const {data: stageInformation, isFetching: isFetchingStageInformation} = useGetStageInformation(sessionID)
    const {data: stageInformation, isFetching: isFetchingStageInformation} = useGetLevelOfSessionByPlayerSessionIDHook({player_session_id : sessionID});
    // const {refetch: refetchCodeResult, data: codeResultData, isFetching: isFetchingCodeResult} = useGetCodeResult(sessionID);
    // const {refetch: reSubmitCode} = useSubmitCode(submittedCodeBody);
    // const {data: roomPinOfSession} = useSessionIdToRoomPin(sessionID);

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

// @ts-ignore
    return (
 isFetchingStageInformation ? <LoadingDisplay /> :

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
         <CodeExectuionDisplay codeExecResponse={codeExecutionResponse} />
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
                     <NodeV2 key={node.node_id} node={node} codeSetter={setCode} />
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