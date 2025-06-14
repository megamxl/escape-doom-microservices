'use client'

import React, {useEffect, useState} from 'react';
import {Backdrop, CircularProgress, Divider, Grid, Grow, Paper, Stack, Typography} from "@mui/material";
import {common} from "@mui/material/colors";
import {redirect} from 'next/navigation'
import UserCard from "./_components/UserCard";
import {LobbyState} from "@/app/types/lobby/LobbyState";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths";
import {initializeStompClient} from "@/app/utils/stompClient.tsx";
import {getSessionStorageItem} from "@/app/utils/session-storage-handler.ts";
import { session_id_key} from "@/app/utils/Constants.ts";
import {escapeRoomStateEnum} from "@/app/gen/session";
import {Client} from '@stomp/stompjs';
import {getSessionData} from "@/app/utils/game-session-handler.ts";

const Lobby = ({lobbyID}: { lobbyID: number }) => {
    const [lobbyState, setLobbyState] = useState<LobbyState>({
        name: '',
        users: [],
        countdown: 5,
        isStarted: false
    })

    const [stompClient, setStompClient] = useState<Client>();

    const [subscribed, setSubscribed] = useState<boolean>(false);


    useEffect(() => {
        if (stompClient && subscribed) {
            sendMessage();
        }
        const storedName = getSessionData();

        if (storedName === null) {
            console.error("make popup playerName empty")
            return
        }

        setLobbyState(prevState => ({
            ...prevState,
            name: storedName.playerName
        }));
        console.debug("storedName: " + storedName);
        console.debug("lobbystate Name: " + lobbyState.name)

    }, [subscribed]);

    useEffect(() => {
        let interval: NodeJS.Timeout;
        const handleVisibilityChange = () => {
            if (document.visibilityState === "visible") {
                interval = setInterval(() => {
                    setLobbyState({...lobbyState, countdown: lobbyState.countdown - 1});
                }, 1000);
            } else {
                clearInterval(interval);
            }
        };

        if (lobbyState.isStarted) {
            document.addEventListener("visibilitychange", handleVisibilityChange);
            handleVisibilityChange();
        }

        if (lobbyState.countdown === 0) {
            setLobbyState({...lobbyState, isStarted: false, countdown: 5})
            redirect(`${GAME_SESSION_APP_PATHS.SESSION}`);
        }

        return () => {
            clearInterval(interval);
            document.removeEventListener("visibilitychange", handleVisibilityChange);
        };
    }, [lobbyState.countdown, lobbyState.isStarted]);


    useEffect(() => {
        const setupWebSocket = async () => {
            try {
                const client = await initializeStompClient();
                if (!client) return;

                setStompClient(client);
                client.onConnect = () => {
                    client.subscribe("/topic/player-names/" + lobbyID, (message) => {
                        const data = JSON.parse(message.body);

                        if (data.state === escapeRoomStateEnum.started.toUpperCase()) {
                            const playerId = getSessionStorageItem(session_id_key);

                            if (playerId === null) {
                                console.error("No Player SessionSet !!!! make pop up")
                            }
                            setLobbyState(prev => ({
                                ...prev,
                                isStarted: true,
                            }))
                            return
                        }

                        setSubscribed(true);
                        if (data.message && Array.isArray(data.message)) {
                            setLobbyState(prevState => ({
                                ...prevState,
                                users: data.message
                            }));
                        }
                    });
                };

                client.onWebSocketError = (error) => {
                    console.error("WebSocket Error:", error);
                };

                client.onStompError = (frame) => {
                    console.error("STOMP Error:", frame.headers["message"]);
                };

                client.activate();
            } catch (error) {
                console.error("Error initializing WebSocket:", error);
            }
        };

        setupWebSocket();

        return () => {
            if (stompClient) {
                stompClient.deactivate();
            }
        };
    }, []);

    const sendMessage = () => {
        if (!stompClient) {
            console.error("STOMP client is not initialized");
            return;
        }
    };

    return (
        <>
            <Paper sx={{width: "50%", margin: "auto", padding: 2, marginY: 2}}>
                <Typography align="center" color={common.white} variant="h4"> Join at {window.location.host} with
                    GamePin: </Typography>
                <Typography align="center" color={common.white} variant="h2"> {lobbyID} </Typography>
            </Paper>
            <Divider/>
            <Stack direction="row" justifyContent="space-between">
                <Stack marginLeft={10} direction="column">
                    <Typography fontSize={"2.5rem"} fontWeight="bold"
                                align="center"> {lobbyState.users.length} </Typography>
                    <Typography fontSize={"1.5rem"} fontWeight="bold"> Players </Typography>
                </Stack>
                <Stack direction="row" alignItems="center">
                    <Typography fontSize={"1rem"} fontWeight="bold"> Waiting for players </Typography>
                    <CircularProgress size={30} thickness={5} sx={{margin: 2, marginRight: 10}}/>
                </Stack>
            </Stack>
            <Grid
                container
                direction="row"
                alignItems="center"
                marginX="auto"
                width="70vw"
                columnSpacing={3}
                rowSpacing={3}
            >
                {lobbyState.users.map((playerName, index) => (

                    <Grid key={index} size={{xs: 4}} p={1}>
                        <UserCard playerName={playerName} isMainUsr={lobbyState.name === playerName}/>
                    </Grid>

                ))
                }
            </Grid>
            <Backdrop TransitionComponent={Grow} open={lobbyState.isStarted}>
                <Stack>
                    <Typography fontSize="8rem"> Starting in </Typography>
                    <Typography align="center" fontSize="10rem"> {lobbyState.countdown} </Typography>
                </Stack>
            </Backdrop>
        </>
    );
};

export default Lobby;