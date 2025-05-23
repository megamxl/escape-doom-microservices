import {formatTime} from "@/app/utils/formatTime";
import {UserProgress} from "@/app/gen/leaderboard";

type RankEntryProps = {
    rankingInfo: UserProgress,
    index: number
}

const LeaderboardRankEntry = ({rankingInfo, index}: RankEntryProps) => {
    const {player_name, score, last_riddle_solved_at} = rankingInfo

    return (
        <div className={"flex flex-row gap-2 justify-between px-4 border-[1px] bg-neutral-900 border-neutral-800 rounded-2xl h-[4.5rem] items-center"}>
            <div className={"flex flex-row gap-3"}>
                <p className={"text-2xl"}> #{index + 1} </p>
                <p className={"text-2xl"}> {player_name} </p>
            </div>
            <div className={"flex flex-row gap-3"}>
                <p className={"text-2xl align-middle"}> {score} Pkt. </p>
                <p className={"text-2xl"}> | </p>
                <p className={`text-2xl ${!last_riddle_solved_at ? 'text-gray-500' : 'text-white'}`}> {formatTime(last_riddle_solved_at) || "00:00:00"} </p>
            </div>
        </div>
    )
}

export default LeaderboardRankEntry;