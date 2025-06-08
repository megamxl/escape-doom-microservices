import {
    getSessionStorageItem,
    removeSessionStorageItem,
    setSessionStorageItem
} from "@/app/utils/session-storage-handler";
import {session_id_key} from "@/app/utils/Constants.ts";
import {RiddleWrapper} from "@/app/gen/player";

export type GameSessionData = {
    sessionID: string,
    roomPin: string,
    playerName: string,
    riddleType: string,
    riddle: RiddleWrapper
}

export function getSessionData(): GameSessionData | null {
    const raw = getSessionStorageItem(session_id_key);
    if (!raw) return null;

    try {
         const parse = JSON.parse(raw) as GameSessionData;
         if (parse.sessionID == "" || parse.playerName == "" || parse.roomPin == "") {
             console.error('Error parsing sessionStorage JSON some things are empty:');
             return null;
         }
         return parse;
    } catch (error) {
        console.error('Error parsing sessionStorage JSON:', error);
        return null;
    }
}

export function setSessionData(value :GameSessionData): Error  | null {
    try {
        const serialized = JSON.stringify(value);
        setSessionStorageItem(session_id_key, serialized);
        return null;
    } catch (error) {
        console.error('Error stringifying sessionStorage JSON:', error);
        return error instanceof Error ? error : new Error(String(error));    }
}

export const removeGameSession = () => {
    removeSessionStorageItem(session_id_key)
}