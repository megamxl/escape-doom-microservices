import React from 'react';
import {UserProgress} from "@/app/gen/leaderboard";
import {formatTime} from "@/app/utils/formatTime.ts";

const PedestalPlace = ({player, place}: { player: UserProgress, place: number }) => {

    const pedestalGradients = [
        "bg-gradient-to-b from-[#efbf04]",
        "bg-gradient-to-b from-[#9AA1AD]",
        "bg-gradient-to-b from-[#6B472F]"
    ]

    const defineHeight = () => {
        switch (place) {
            case 1: return "h-[200px]"
            case 2: return "h-[150px]"
            case 3: return "h-[100px]"
        }
    }

    return (
        player &&
        <div className={"flex flex-col flex-grow items-center gap-2"}>
            <p className={`text-3xl ${!player.last_riddle_solved_at ? 'text-gray-500' : 'text-white'}`}> {player.score} Pkt. | {formatTime(player.last_riddle_solved_at)} </p>
            <p> {player.player_name} </p>
            <div
                className={`flex justify-center items-center ${pedestalGradients[place - 1]} ${defineHeight()} rounded-t-2xl w-full `}>
                <p className={"text-4xl font-bold"}> #{place} </p>
            </div>
        </div>
    );
};

export default PedestalPlace;