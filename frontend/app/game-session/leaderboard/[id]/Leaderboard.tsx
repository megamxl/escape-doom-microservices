'use client'

import React, {useEffect, useState} from 'react';
import TopThree from "@/app/game-session/leaderboard/[id]/_components/TopThree";
import LeaderboardRankEntry from "@/app/game-session/leaderboard/[id]/_components/LeaderboardRankEntry";
import {formatTimeInt} from "@/app/utils/formatTime";
import {useGetRoomPinHook, UserProgress} from "@/app/gen/leaderboard";
import {useGetERSessionByPinHook} from "@/app/gen/session";

const Leaderboard = ({roomPin}: { roomPin: number }) => {

    const {data: leaderboardData, isFetching: isFetching} = useGetRoomPinHook({room_pin: roomPin});
    const {data: sessionInfo, isFetching: isSessionFetching} = useGetERSessionByPinHook({pin: roomPin})

    const [remainingTime, setRemainingTime] = useState<number | null>(null)

    useEffect(() => {
        if (sessionInfo?.end_time) {

            const targetTime = new Date(sessionInfo.end_time)
            const curDate = new Date();

            console.log(curDate.getTime())
            console.log(targetTime.getTime())

            console.log(Math.floor((targetTime.getTime() - curDate.getTime()) / (1000 * 60)))

            const diffMs = targetTime.getTime() - curDate.getTime()

            setRemainingTime(diffMs)
        }
    }, [sessionInfo]);

    useEffect(() => {
        if (remainingTime && remainingTime <= 0) return;

        // Creates interval that subtracts 1 every second => Countdown
        const interval = setInterval(() => {
            setRemainingTime(prev => Math.max(prev - 1000, 0));
        }, 1000)

        return () => clearInterval(interval)
    }, [remainingTime]);

    const sortUsersByScore = (users: UserProgress[]): UserProgress[] =>
        [...users].sort((a, b) => (b.score ?? 0) - (a.score ?? 0));

    return (

        <div className={"flex flex-col w-5/6 lg:w-1/2 mt-4 gap-8 justify-center m-auto"}>
            {
                !isSessionFetching && remainingTime ?
                    <p className={"text-8xl font-bold self-center"}>
                        {formatTimeInt(remainingTime / 1000)}
                    </p> :
                    <p className="text-5xl font-bold self-center">
                        {roomPin} - Not started yet
                    </p>
            }

            {!isFetching && leaderboardData ?
                <TopThree topThree={sortUsersByScore(leaderboardData)!.slice(0, 3)}/> : "Loading"}

            <div className={"flex flex-col gap-3"}>
                {
                    !isFetching && leaderboardData ? sortUsersByScore(leaderboardData).slice(3).map((player, idx) => {
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