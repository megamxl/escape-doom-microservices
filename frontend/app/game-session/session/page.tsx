import React from 'react';
import Session from "@/app/game-session/session/Session.tsx";

type SessionProps = {
    params: Promise<{id: string}>
}

const SessionPage = () => {
    return (
        <Session />
    );
};

export default SessionPage;