'use client'

import React, {useEffect, useState} from 'react';
import {
    Button,
    CircularProgress,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Grow,
    Stack,
    Typography
} from "@mui/material";
import {redirect, useRouter} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths.ts";
import {CodingRiddleDTO, SceneDTO, useGetLevelOfSessionByPlayerSessionIDHook} from "@/app/gen/player";
import Node from "@/app/game-session/session/_components/nodes/Node.tsx";
import ErrorDisplayCard, {ErrorDetails} from "@/app/game-session/session/_components/ErrorDisplayCard.tsx";
import {GameSessionData, getSessionData, removeGameSession} from "@/app/utils/game-session-handler.ts";
import CodingRiddle from "@/app/game-session/session/_components/CodingRiddle.tsx";

const Session = () => {

    const [currentScene, setCurrentScene] = useState<SceneDTO>()
    const [sessionData, setSessionData] = useState<GameSessionData>({
        sessionID: "",
        roomPin: "",
        playerName: "",
        riddleType: "CODING",
        riddle: {
            type: "CODING",
            code: {
                java: `public class HelloWorld {
            public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}`
            }
        }
    })

    useEffect(() => {
        const sessionStorageItem = getSessionData();
        if (sessionStorageItem !== null) {
            if (!sessionStorageItem || !sessionStorageItem.sessionID || sessionStorageItem.sessionID === "" || !sessionStorageItem.playerName || sessionStorageItem.playerName === "" || !sessionStorageItem.roomPin) {
                redirect(GAME_SESSION_APP_PATHS.STUDENT_JOIN)
            }

            setSessionData(sessionStorageItem)
        }
    }, [])

    /* TanStack Query Calls */
    const {
        data: stageInformation,
        isFetching: isFetchingStageInformation,
        isError: errorState,
        error: errorObject
    } = useGetLevelOfSessionByPlayerSessionIDHook({player_session_id: sessionData!.sessionID});


    useEffect(() => {
        if (stageInformation?.riddle?.riddle === undefined || stageInformation.scenes === undefined) return;

        if (stageInformation?.riddle?.riddle.type === "CODING") {
            const codingRiddle = stageInformation?.riddle?.riddle as CodingRiddleDTO
            setSessionData(prev => ({
                ...prev,
                riddle: codingRiddle,
                riddleType: codingRiddle.type
            }))
        }

        setCurrentScene(stageInformation.scenes.find(s => s.scene_sequence == 1))
    }, [stageInformation])

    const handleZoomChange = (targetSceneId: string) => {
        if (stageInformation?.scenes === undefined) return;
        const newIdx = stageInformation?.scenes.findIndex(scene => scene.scene_id == targetSceneId) ?? -1

        if (newIdx !== -1) {
            console.log("New scene: ", stageInformation?.scenes[newIdx])
            setCurrentScene(stageInformation?.scenes[newIdx])
        } else {
            console.warn(`Scene with ID ${targetSceneId} not found`)
        }
    }

// Error and loading display before returning the content
    if (isFetchingStageInformation) {
        return <LoadingDisplay/>;
    }


    if (errorState) {
        const errorDetails: ErrorDetails = errorObject?.response?.data || {
            timestamp: new Date().toISOString(),
            status: 500,
            error: "Internal Server Error",
            message: "Something went wrong",
            path: "unknown",
        };

        return (
            <ErrorDisplayCard
                errorDetails={errorDetails}
                onRetry={() => console.log("retry")}
                onBack={() => console.log("To Join")}
            />
        );

    }

    return (
        <>
            <Stack direction="row" alignItems="center" height="100vh">

                {sessionData.riddleType === "CODING" &&
                    <>
                        <CodingRiddle riddlePassed={sessionData.riddle as CodingRiddleDTO} session={sessionData} />
                    </>
                }

                <div className="relative w-full mx-auto">
                    <img
                        //@ts-ignore
                        src={`${currentScene?.background_image_uri}`}
                        alt="Background"
                        className="w-full bg-no-repeat bg-contain"
                    />
                    {
                        //@ts-ignore
                        currentScene?.nodes.map((node) => {
                            return (
                                <Node key={node.node_id} node={node} onZoomChangeScene={handleZoomChange}/>
                            )
                        })
                    }
                </div>
            </Stack>
        </>

    );
};

const LoadingDisplay = () => {
    return (
        <Stack height={"100vh"} width={"100vw"} direction={"column"} justifyContent={"center"} gap={4}
               alignItems={"center"}>
            <CircularProgress size={"10rem"} variant={"indeterminate"}/>
            <Typography variant={"h3"}> Loading... </Typography> </Stack>
    )
}

export default Session;