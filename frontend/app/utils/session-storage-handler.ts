import {SetStateAction} from "react";

// Guard clause to prevent error as in dev mode - Rendering is both done on server & client
// while sessionStorage is only available on client
const isBrowser = (): boolean => typeof window !== "undefined";

export const getSessionStorageItem = (key: string): string | null => {
    if (!isBrowser()) return null

    try {
        const item = sessionStorage.getItem(key);
        return item ?? null;
    } catch (error) {
        console.error("Error reading sessionStorage key:", error);
        return null ;
    }
};

export const setSessionStorageItem = (key: string, value: string, fun?: (action: SetStateAction<string>) => void)  => {
    if (!isBrowser()) return

    try {
        if (fun) {
            fun(value);
        }
        sessionStorage.setItem(key, value);
    } catch (error) {
        console.error("Error setting sessionStorage key:", error);
    }
};

export const removeSessionStorageItem = (key: string) => {
    if (!isBrowser()) return

    try {
        sessionStorage.removeItem(key);
    } catch (error) {
        console.error("Error removing sessionStorage key:", error);
    }
};
