import {useMutation} from "@tanstack/react-query";
import axios from "axios";
import {SESSION_API} from "@/app/constants/paths.ts";

export const useAddTagToSession = () =>
    useMutation({
        mutationFn: ({sessionId, tag}: {sessionId: string, tag: string}) =>
            axios
                .put(SESSION_API.ADD_TAG(sessionId, tag), null, {
                    withCredentials: true
                })
                .then(res => res.data)
    });
