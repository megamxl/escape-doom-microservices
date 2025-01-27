import React from 'react';
import Test from "@/app/testpage/Test";

type LobbyProps = {
    params: Promise<{id: number}>
}

const TestPage = () => {

    return (
        <Test />
    );
};

export default TestPage;