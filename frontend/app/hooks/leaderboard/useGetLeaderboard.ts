import {useQuery} from "@tanstack/react-query";
import {getLeaderboard} from "@/app/api/gameSession/leaderboards";

export const useGetLeaderboard = (lobbyId: number) => {
    return useQuery({
        queryKey: ['leaderboard'],
        queryFn: () => getLeaderboard(lobbyId),
        refetchInterval: 1000 * 10 // in millis
    })
}