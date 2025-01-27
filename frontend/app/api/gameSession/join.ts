import {gameSessionClient} from "@/app/api/axios";
import {LobbyJoinResponse} from "@/app/types/student-join/lobby-join-response";
import {LobbyStatusResponse} from "@/app/types/student-join/lobby-status-response";
import {StageInfoResponse} from "@/app/types/game-session/stage-info-response";
import {SubmittedCodeBody} from "@/app/types/game-session/SubmittedCodeBody";
import {CodeExecResponse} from "@/app/types/game-session/CodeExecResponse";

const ENDPOINT = "/join"

export const postJoinLobby = async (roomPin: string): Promise<LobbyJoinResponse> => {
    const { data } = await gameSessionClient.get(`${ENDPOINT}/${roomPin}`);
    return data;
}

export const getLobbyStatus = async (roomPin: string): Promise<LobbyStatusResponse> => {
    const { data } = await gameSessionClient.get(`${ENDPOINT}/status/${roomPin}`)
    return data;
}

export const joinLobby = async (sessionID: string): Promise<void> => {
    const { data } = await gameSessionClient.get(`${ENDPOINT}/lobby/${sessionID}`)
    return data;
}

export const getStageInformation = async (sessionID: string): Promise<StageInfoResponse> => {
    const { data } = await gameSessionClient.get(`${ENDPOINT}/getStage/${sessionID}`)
    return data;
}

export const postSubmitCode = async (codeBody: SubmittedCodeBody) => {
    console.log("CodeBody", codeBody)
    const { data } = await gameSessionClient.post(`${ENDPOINT}/submitCode`, codeBody)
    return data;
}

export const getCodeResult = async (sessionID: string): Promise<CodeExecResponse> => {
    const { data } = await gameSessionClient.get(`${ENDPOINT}/getCode/${sessionID}`)
    return data;
}

export const sessionIdToRoomPin = async (sessionID: string): Promise<string> => {
    const { data } = await gameSessionClient.get(`${ENDPOINT}/sessionToRoomPin/${sessionID}`)
    return data;
}