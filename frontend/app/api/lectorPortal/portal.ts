import {lectorClient} from "@/app/api/axios";
import {EscapeRoom} from "@/app/types/dashboard/EscapeRoom";
import {LECTOR_SESSION_STORAGE_STRING} from "@/app/utils/lector-token-handler";
import {AxiosRequestConfig} from "axios";
import {RoomState} from "@/app/enums/RoomState";
import {getSessionStorageItem} from "@/app/utils/session-storage-handler";

const ENDPOINT = "/portal-escape-room"

const setAuthCurrentSessionToken = () => {
    const headers: AxiosRequestConfig = {
        headers: {Authorization: `Bearer ${getSessionStorageItem(LECTOR_SESSION_STORAGE_STRING)}`}
    }
    return headers;
}

const getAllRooms = async (): Promise<EscapeRoom[]> => {
    const headers = setAuthCurrentSessionToken();
    const {data} = await lectorClient.get(`${ENDPOINT}/getAll`, headers);
    return data;
}

const changeRoomState = async (state: RoomState, id: number, time?: number) => {
    switch (state) {
        case RoomState.PLAYING: return (await startRoom(id, time!));
        case RoomState.JOINABLE: return (await openRoom(id));
        case RoomState.STOPPED: return (await stopRoom(id));
        default: console.error("No valid state given", state); return "-1";
    }
}

const openRoom = async (id: number) => {
    const headers = setAuthCurrentSessionToken();
    console.log("Headers:", headers)
    const {data} = await lectorClient.post(`${ENDPOINT}/openEscapeRoom/${id}`, null, headers)
    return data;
}

const startRoom = async (id: number, escapeRoomTime: number) => {
    const headers = setAuthCurrentSessionToken();
    const {data} = await lectorClient.post(`${ENDPOINT}/startEscapeRoom/${id}/${escapeRoomTime}`, null, headers)
    return data;
}

const stopRoom = async (id: number) => {
    const headers = setAuthCurrentSessionToken();
    const {data} = await lectorClient.post(`${ENDPOINT}/stopEscapeRoom/${id}`, null, headers)
    return data;
}

export {getAllRooms, changeRoomState}