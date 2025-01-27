import { useQuery } from "@tanstack/react-query";
import axios from "axios";

const sleep = (ms: number) => new Promise(r => setTimeout(r, ms));

interface leaderboardScores {
    score: number,
    playerName: string,
    time: number
}

export const getLeaderboardScores = (url: string) => {

    let headers = {}

    return useQuery({
        queryKey: ['getLeaderboardScores'],
        retry: 1,
        queryFn: async () => {
            const { data } = await axios.get(url, { headers })
            return data as [leaderboardScores]
        },
        refetchInterval: 1000 * 60,
        staleTime: Infinity,
        /*onSuccess(data) {
            console.log("Fetched new data " + new Date())
        },*/
    })
};