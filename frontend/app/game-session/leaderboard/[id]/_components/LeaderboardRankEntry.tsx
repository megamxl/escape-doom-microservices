import {PlayerProgression} from "@/app/types/leaderboard/player-progression";
import {formatTime} from "@/app/utils/formatTime";

type RankEntryProps = {
    rankingInfo: PlayerProgression,
    index: number
}

const LeaderboardRankEntry = ({rankingInfo, index}: RankEntryProps) => {
    const {playerName, score, time} = rankingInfo

    return (
        <div className={"flex flex-row gap-2 justify-between px-4 border-[1px] bg-neutral-900 border-neutral-800 rounded-2xl h-[4.5rem] items-center"}>
            <div className={"flex flex-row gap-3"}>
                <p className={"text-2xl"}> #{index + 1} </p>
                <p className={"text-2xl"}> {playerName} </p>
            </div>
            <div className={"flex flex-row gap-3"}>
                <p className={"text-2xl align-middle"}> {score} Pkt. </p>
                <p className={"text-2xl"}> | </p>
                <p className={`text-2xl ${!time ? 'text-gray-500' : 'text-white'}`}> {formatTime(time) || "00:00:00"} </p>
            </div>
        </div>
    )
}

export default LeaderboardRankEntry;