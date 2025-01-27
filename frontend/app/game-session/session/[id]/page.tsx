import React from 'react';
import Session from "@/app/game-session/session/[id]/Session";

type SessionProps = {
    params: Promise<{id: string}>
}

const SessionPage = async ({params}: SessionProps) => {

    const sessionID = (await params).id

    return (
        <Session sessionID={sessionID} />
    );
};

export default SessionPage;