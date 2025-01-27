import {gameSessionClient} from "@/app/api/axios";
import {PlayerProgression} from "@/app/types/leaderboard/player-progression";

const ENDPOINT = "/leaderboard"

export const getLeaderboard = async (lobbyId: number): Promise<PlayerProgression[]> => {
    let { data } = await gameSessionClient.get<PlayerProgression[]>(`${ENDPOINT}/${lobbyId}`);
    // data.push(...data); // For testing
    // data[2].score = 200; // For testing
    return sortLeaderboardData(data);
}

const sortLeaderboardData = (data: PlayerProgression[]) => {
    return data?.sort((a,b) => {
        // b - a for descending order
        if (a.score !== b.score) return b.score - a.score
        return b.time ?? 0 - a.time ?? a.time
    });
}