import {useQuery} from "@tanstack/react-query";
import {postJoinLobby} from "@/app/api/gameSession/join";

export const useLobbyJoin = (sessionID: string) => {
    return useQuery({
        queryKey: ["join-lobby"],
        queryFn: () => postJoinLobby(sessionID),
        enabled: false
    })
}