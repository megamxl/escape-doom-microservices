import {useQuery} from "@tanstack/react-query";
import {joinLobby} from "@/app/api/gameSession/join";

export const useJoinLobby = (sessionID: string) => {
    return useQuery({
        queryKey: ['join-lobby', sessionID],
        queryFn: () => joinLobby(sessionID)
    })
}