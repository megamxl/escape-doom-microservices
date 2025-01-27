import {useState} from "react";
import {getSessionStorageItem, setSessionStorageItem} from "@/app/utils/session-storage-handler";

export const LECTOR_SESSION_STORAGE_STRING = 'token';

export const useLectorToken = () => {
    const [token, setToken] = useState<string>(() => {
        return getSessionStorageItem(LECTOR_SESSION_STORAGE_STRING) ?? ""
    })

    const setLectorToken = (newToken: string) => {
        setSessionStorageItem(LECTOR_SESSION_STORAGE_STRING, newToken, setToken)
    }

    return [token, setLectorToken] as const;
}
