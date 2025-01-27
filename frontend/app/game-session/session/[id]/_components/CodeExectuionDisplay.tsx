import React from 'react';
import EditorContainer from "@/app/game-session/session/[id]/_components/EditorContainer";
import {Box, Button, Stack, Typography} from "@mui/material";
import {CodeExecResponse} from "@/app/types/game-session/CodeExecResponse";
import {green} from "@mui/material/colors";
import {CompileStatus} from "@/app/enums/CompileStatus";

type CodeExecutionDisplayProps = {
    codeExecResponse: CodeExecResponse
}

const CodeExectuionDisplay = ({codeExecResponse}: CodeExecutionDisplayProps) => {
    return (
        <EditorContainer sx={{marginBottom: 1, height: "112px", maxHeight: "112px", overflow: "auto"}}>
            <Stack direction="column">
                { codeExecResponse.status === CompileStatus.SUCCESS ?
                    <Box sx={{backgroundColor: green[300]}} p={1}>
                        <Typography color={green[900]}> Success! </Typography>
                        <Typography color={green[900]}> You solved this riddle, continue to the next one ? </Typography>
                        <Button sx={{color: green[900], fontWeight: 'bold'}} onClick={() => window.location.reload()}> Take me there </Button>
                    </Box>
                    :
                    <></>
                }
                <Box>
                    <Typography> Console Output </Typography>
                </Box>
                <Box overflow={"auto"}>
                    <Typography fontSize={"0.9rem"} fontWeight={"bold"} color={codeExecResponse.status === CompileStatus.ERROR ? '#f00' : '#fff'}>
                        {
                            codeExecResponse.status === CompileStatus.ERROR ? 'Error Msg' : 'Output'
                        }
                    </Typography>
                    <Typography component="pre"> {'> '}{codeExecResponse.output} </Typography>
                </Box>
            </Stack>
        </EditorContainer>
    );
};

export default CodeExectuionDisplay;