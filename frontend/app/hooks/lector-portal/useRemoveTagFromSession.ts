import {useMutation} from "@tanstack/react-query";
import {removeTagFromSession} from "@/app/api/lectorPortal/tags.ts";

export const useRemoveTagFromSession = () =>
    useMutation({
        mutationFn: ({sessionId, tag}: {sessionId: string, tag: string}) =>
            removeTagFromSession(sessionId, tag)
    });