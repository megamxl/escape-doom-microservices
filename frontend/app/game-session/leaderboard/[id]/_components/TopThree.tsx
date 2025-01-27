import React from 'react';
import {PlayerProgression} from "@/app/types/leaderboard/player-progression";
import PedestalPlace from "@/app/game-session/leaderboard/[id]/_components/PedestalPlace";

type TopThreeProps = {
    topThree: PlayerProgression[]
}

const TopThree = ({topThree}: TopThreeProps) => {
    const [first, second, third] = topThree.slice(0, 3);

    return (
        <div className={"flex flex-row items-end gap-4"}>
            <PedestalPlace player={second} place={2} />
            <PedestalPlace player={first} place={1} />
            <PedestalPlace player={third} place={3} />
        </div>
    );
};

export default TopThree;