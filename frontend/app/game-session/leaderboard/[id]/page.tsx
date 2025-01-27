import React from 'react';
import Leaderboard from "@/app/game-session/leaderboard/[id]/Leaderboard";

type LeaderBoardProps = {
    params: Promise<{id: number}>
}

const LeaderboardPage = async ({params}: LeaderBoardProps) => {

    const id = (await params).id;

    return (
        <Leaderboard boardID={id} />
    );
};

export default LeaderboardPage;