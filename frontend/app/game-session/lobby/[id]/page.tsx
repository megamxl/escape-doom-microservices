import React from 'react';
import Lobby from "@/app/game-session/lobby/[id]/Lobby";

type LobbyProps = {
    params: Promise<{id: number}>
}

const LobbyPage = async ({params}: LobbyProps) => {

    const id = (await params).id

    return (
        <Lobby lobbyID={id} />
    );
};

export default LobbyPage;