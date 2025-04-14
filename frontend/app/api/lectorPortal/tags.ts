import {sessionClient} from "@/app/api/axios";
import {EscapeRoomSessionResponse} from "@/app/gen/session";

const ENDPOINT = "/tag";

const requestWithSession = {
    withCredentials: true
};

export const addTagToSession = async (sessionId: string, tag: string): Promise<EscapeRoomSessionResponse> => {
    const { data } = await sessionClient.put(`${ENDPOINT}/${sessionId}/${tag}`, null, requestWithSession);
    return data;
};

export const removeTagFromSession = async (sessionId: string, tag: string): Promise<EscapeRoomSessionResponse> => {
    const { data } = await sessionClient.delete(`${ENDPOINT}/${sessionId}/${tag}`, requestWithSession);
    return data;
};