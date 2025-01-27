import {useQuery} from "@tanstack/react-query";
import {sessionIdToRoomPin} from "@/app/api/gameSession/join";

export const useSessionIdToRoomPin = (sessionID: string) => {
    return useQuery({
        queryKey: ['sessionToRoomPin', sessionID],
        queryFn: () => sessionIdToRoomPin(sessionID)
    })
}