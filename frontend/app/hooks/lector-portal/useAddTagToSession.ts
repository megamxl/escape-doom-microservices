import {useMutation} from "@tanstack/react-query";
import {addTagToSession} from "@/app/api/lectorPortal/tags.ts";

export const useAddTagToSession = () =>
    useMutation({
        mutationFn: ({sessionId, tag}: {sessionId: string, tag: string}) =>
            addTagToSession(sessionId, tag)
    });
