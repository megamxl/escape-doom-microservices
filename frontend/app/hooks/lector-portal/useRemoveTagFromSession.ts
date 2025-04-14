import {useMutation} from "@tanstack/react-query";
import axios from "axios";
import {EscapeRoomSessionResponse} from "@/app/gen/session";
import {SESSION_API} from "@/app/constants/paths.ts";

export const useRemoveTagFromSession = () =>
    useMutation({
        mutationFn: ({ sessionId, tag }: { sessionId: string, tag: string }) =>
            axios
                .delete<EscapeRoomSessionResponse>(
                    SESSION_API.REMOVE_TAG(sessionId, tag),
                    {
                        withCredentials: true
                    }
                )
                .then(res => res.data)
    });