import {useQuery} from "@tanstack/react-query";
import {SubmittedCodeBody} from "@/app/types/game-session/SubmittedCodeBody";
import {postSubmitCode} from "@/app/api/gameSession/join";

//TODO: useMutation instead of useQuery
export const useSubmitCode = (codeBody: SubmittedCodeBody) => {
    return useQuery({
        queryKey: ['submit-code', codeBody],
        queryFn: () => postSubmitCode(codeBody),
        enabled: false
    })
}