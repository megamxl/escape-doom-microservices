'use client'

import React, {ChangeEvent, FormEvent, useState} from 'react';
import {Alert, Button, Card, CardContent, Grid2, Snackbar, Stack, TextField, Typography} from "@mui/material";
import BackgroundImage from '@/public/images/StudentJoin.jpg'
import {common} from '@mui/material/colors';
import {redirect, useRouter} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths";
import {useSession} from "@/app/utils/game-session-handler";
import {
    EscapeRoomJoin, EscapeRoomJoinResponse,
    EscapeRoomStateEnum,
    escapeRoomStateEnum,
    HandlePlayerJoinMutationResponse,
    useHandlePlayerJoinHook
} from "@/app/gen/player";

const StudentJoin = () => {
    const appRouterInstance = useRouter();

    const [roomJoin, setRoomJoin] = useState<EscapeRoomJoin>()

    const [openSnackbar, setOpenOpenSnackbar] = useState(false);

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

        playerJoinCall({data: roomJoin},{
            onSuccess: (response) => {
                if (response.player_session_id === undefined){
                    setOpenOpenSnackbar(true)
                    return
                }
                switch (response.escape_room_state) {
                    case escapeRoomStateEnum.started:
                        setSession(response.player_session_id!)
                        appRouterInstance.push(`${GAME_SESSION_APP_PATHS.SESSION}/${response.player_session_id}`)
                        break;
                    case escapeRoomStateEnum.open:
                        setSession(response.player_session_id!)
                        console.log("should redirect ", `${GAME_SESSION_APP_PATHS.LOBBY}/${roomJoin?.room_pin}`)
                        appRouterInstance.push(`${GAME_SESSION_APP_PATHS.LOBBY}/${roomJoin?.room_pin}`)
                        break;
                    case escapeRoomStateEnum.closed || escapeRoomStateEnum.finished:
                        setSession("")
                        setOpenOpenSnackbar(true)
                        break;
                    default:
                        console.log("Lobby is in an unknown state");
                }
                console.log(response)
            },
            onError: (error) =>{
                setOpenOpenSnackbar(true)
                return
            }
        })
    }

    return (
        <>
            <Grid2
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
                            <Button sx={{height: 56}} variant="contained" type="submit" fullWidth>JOIN</Button>
                        </Stack>
                    </CardContent>
                </Card>
            </Grid2>

            <Snackbar open={openSnackbar} autoHideDuration={6000} onClose={() => setOpenOpenSnackbar(false)}>
                <Alert onClose={() => setOpenOpenSnackbar(false)} severity="error" sx={{width: '100%'}}>
                    The given lobby is either closed or doesn't exist
                </Alert>
            </Snackbar>
        </>
    );
};

export default StudentJoin;