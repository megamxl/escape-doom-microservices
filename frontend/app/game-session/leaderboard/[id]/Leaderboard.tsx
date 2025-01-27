'use client'

import React, {useEffect, useState} from 'react';
import {useGetLeaderboard} from "@/app/hooks/leaderboard/useGetLeaderboard";
import TopThree from "@/app/game-session/leaderboard/[id]/_components/TopThree";
import LeaderboardRankEntry from "@/app/game-session/leaderboard/[id]/_components/LeaderboardRankEntry";
import {formatTime} from "@/app/utils/formatTime";

const Leaderboard = ({boardID}: { boardID: number }) => {

    const {data} = useGetLeaderboard(boardID);
    const startTime = 1000 * 60 * 60 * 2 // TODO: Let backend give me this info
    const [remainingTime, setRemainingTime] = useState(startTime) // 2 hours TODO: Let backend give me this info

    useEffect(() => {
        if (remainingTime <= 0) return;

        // Creates interval that subtracts 1 every second => Countdown
        const interval = setInterval(() => {
            setRemainingTime(prev => Math.max(prev - 1000, 0));
        }, 1000)

        return () => clearInterval(interval)
    }, [remainingTime]);

    useEffect(() => {
        console.log(data)
    }, [data]);

    return (
        <div className={"flex flex-col w-5/6 lg:w-1/2 mt-4 gap-8 justify-center m-auto"}>
            <p className={"text-8xl font-bold self-center"}> {formatTime(remainingTime / 1000)} </p>

            {data ? <TopThree topThree={data.slice(0, 3)}/> : "Loading"}

            <div className={"flex flex-col gap-3"}>
                {
                    data ? data.slice(3).map((player, idx) => {
                        return (
                            <LeaderboardRankEntry rankingInfo={player} index={idx + 3} key={idx + 3}/>
                        )
                    }) : null
                }
            </div>
        </div>
    );
};

export default Leaderboard;