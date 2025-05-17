'use client'

import React, {ChangeEvent, FormEvent, useState} from 'react';
import {Alert, Button, Card, CardContent, Grid, Link, Snackbar, Stack, TextField, Typography} from "@mui/material";
import BackgroundImage from '@/public/images/StudentJoin.jpg'
import {common} from '@mui/material/colors';
import {redirect, useRouter} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths";
import {useSession} from "@/app/utils/game-session-handler";
import {
    EscapeRoomJoin,
    EscapeRoomJoinResponse,
    EscapeRoomStateEnum,
    escapeRoomStateEnum,
    HandlePlayerJoinMutationResponse,
    useHandlePlayerJoinHook
} from "@/app/gen/player";
import {getSessionStorageItem, setSessionStorageItem} from "@/app/utils/session-storage-handler.ts";
import {player_name_key, session_id_key} from "@/app/utils/Constants.ts";

const StudentJoin = () => {
    const appRouterInstance = useRouter();

    const [roomJoin, setRoomJoin] = useState<EscapeRoomJoin>()

    const [openSnackbar, setOpenOpenSnackbar] = useState({state :false, message:"The given lobby is either closed or doesn't exist"})

    const [JoinButton, setJoinButton] = useState<boolean>(false)

    const [session, setSession] = useSession();

    const {mutate : playerJoinCall} = useHandlePlayerJoinHook();

    const handleRoomPinChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setRoomJoin(cur => ({
            ...cur,
            room_pin: Number(e.target.value)
        }));
    }
    const handlePlayerNameChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setRoomJoin(cur => ({
            ...cur,
            player_name: e.target.value
        }));
    }

    const sendID = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setJoinButton(true)

        playerJoinCall({data: roomJoin},{
            onSuccess: (response) => {
                if (response.player_session_id === undefined){
                    setOpenOpenSnackbar(prev => ({ ...prev, state: true }))
                    return
                }
                switch (response.escape_room_state) {
                    case escapeRoomStateEnum.started:
                        setSession(response.player_session_id!)
                        appRouterInstance.push(GAME_SESSION_APP_PATHS.SESSION)
                        break;
                    case escapeRoomStateEnum.open:
                        setSession(response.player_session_id!)
                        //TODO failure Handle
                        setSessionStorageItem(player_name_key , response.player_name != null ? response.player_name : "");
                        appRouterInstance.push(`${GAME_SESSION_APP_PATHS.LOBBY}/${roomJoin?.room_pin}`)
                        break;
                    case escapeRoomStateEnum.closed || escapeRoomStateEnum.finished:
                        setSession("")
                        setOpenOpenSnackbar(prev => ({ ...prev, state: true }))
                        break;
                    default:
                        console.log("Lobby is in an unknown state");
                }
                console.log(response)
            },
            onError: (error) =>{
                // @ts-ignore
                setOpenOpenSnackbar(prev => ({ ...prev, state: true, message: error.response.data.message }))
                return
            }
        })
    }

    const handleReconnect = () => {
       const sessionStorageItem = getSessionStorageItem(session_id_key);
       const playerNameItem = getSessionStorageItem(player_name_key);

       if(sessionStorageItem === null || playerNameItem === null){
           setOpenOpenSnackbar(prev => ({ ...prev, state: true, message: "No session_id or player_name found can't reconnect " }))
           return
       }

        redirect(GAME_SESSION_APP_PATHS.SESSION)

    }

    return (
        <>
            <Grid
                container
                direction="column"
                justifyContent="center"
                alignItems="center"
                height="100vh"
                sx={{
                    backgroundImage: `url(${BackgroundImage.src})`,
                    backgroundColor: '#404040',
                    backgroundBlendMode: 'multiply',
                    backgroundSize: 'cover',
                }}
            >
                <Typography sx={{paddingBottom: 3}} color={common.white} variant="h2"> Escape Room </Typography>
                <Card sx={{minWidth: 550, paddingX: 3}}>
                    <CardContent>
                        <Stack spacing={2} alignItems="center" component="form" onSubmit={sendID} noValidate>
                            <TextField
                                slotProps={{input: {type: 'number'}}}
                                type="number"
                                id="outlined-basic"
                                label="Room-PIN"
                                variant="outlined"
                                value={roomJoin?.room_pin}
                                onChange={handleRoomPinChange}
                                fullWidth
                            />
                            <TextField
                                type="text"
                                id="outlined-basic"
                                label="Username"
                                variant="outlined"
                                value={roomJoin?.player_name}
                                onChange={handlePlayerNameChange}
                                fullWidth
                            />
                            <Button sx={{height: 56}} variant="contained" type="submit" fullWidth disabled={JoinButton}>JOIN</Button>
                        </Stack>
                        <Stack>
                        <Link
                            component="button"
                            variant="body2"
                            onClick={() => {
                                handleReconnect()
                            }}
                        >
                            I want to reconnect
                        </Link>
                        </Stack>
                    </CardContent>
                </Card>
            </Grid>

            <Snackbar open={openSnackbar.state} autoHideDuration={6000} onClose={() => {
                setOpenOpenSnackbar(prev => ({ ...prev, state: false }))
                setJoinButton(false)
            }
            }>
                <Alert onClose={() => {
                    setOpenOpenSnackbar(prev => ({ ...prev, state: false }))
                    setJoinButton(false)
                }} severity="error" sx={{width: '100%'}}>
                    {openSnackbar.message}
                </Alert>
            </Snackbar>
        </>
    );
};

export default StudentJoin;