import {useQuery} from "@tanstack/react-query";
import {getStageInformation} from "@/app/api/gameSession/join";

export const useGetStageInformation = (sessionID: string) => {
    return useQuery({
        queryKey: ['stage-info', sessionID],
        queryFn: () => getStageInformation(sessionID)
    })
}