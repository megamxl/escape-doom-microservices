import {useState} from "react";
import {
    getSessionStorageItem,
    removeSessionStorageItem,
    setSessionStorageItem
} from "@/app/utils/session-storage-handler";
import {session_id_key} from "@/app/utils/Constants.ts";


export const useSession = () => {
    const [session, setSession] = useState<string>((): string => {
        return getSessionStorageItem(session_id_key) || ""
    })

    const setGameSession = (newSession: string) => {
        setSessionStorageItem(session_id_key, newSession, setSession)
    }
    return [session, setGameSession] as const;
}

export const removeGameSession = () => {
    removeSessionStorageItem(session_id_key)
}