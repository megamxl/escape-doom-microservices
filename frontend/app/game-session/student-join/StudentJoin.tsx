'use client'

import React, {ChangeEvent, FormEvent, useState} from 'react';
import {Alert, Button, Card, CardContent, Grid2, Snackbar, Stack, TextField, Typography} from "@mui/material";
import BackgroundImage from '@/public/images/StudentJoin.jpg'
import {common} from '@mui/material/colors';
import {redirect} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths";
import {useSession} from "@/app/utils/game-session-handler";
import {
    EscapeRoomJoin,
    EscapeRoomStateEnum,
    escapeRoomStateEnum,
    HandlePlayerJoinMutationResponse,
    useHandlePlayerJoinHook
} from "@/app/gen/player";

const StudentJoin = () => {

    const [roomJoin, setRoomJoin] = useState<EscapeRoomJoin>()

    const [openSnackbar, setOpenOpenSnackbar] = useState(false);

    const [session, setSession] = useSession();

    const {mutateAsync, isError, error, data} = useHandlePlayerJoinHook();


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
        console.log("Trying to get lobby of id: " + roomJoin?.room_pin + "Current session: ", session)

        const { player_name, player_session_id, escape_room_state } = await mutateAsync({data: roomJoin})

        if (player_name == null || player_session_id == null || escape_room_state == null) return;


        if (isEscapeRoomOpen(escape_room_state!)) redirect(`${GAME_SESSION_APP_PATHS.SESSION}/${player_session_id}`)

        if (isError) {
            setOpenOpenSnackbar(true)
            console.error(error)
        } else if (data) {
            const responseSessionID = player_session_id

            console.log(data)

            switch (data.escape_room_state) {
                case escapeRoomStateEnum.started:
                    setSession(player_session_id)
                    redirect(`${GAME_SESSION_APP_PATHS.SESSION}/${responseSessionID}`)
                    break;
                case escapeRoomStateEnum.open:
                    setSession(responseSessionID)
                    redirect(`${GAME_SESSION_APP_PATHS.LOBBY}/${roomJoin?.room_pin}`)
                    break;
                case escapeRoomStateEnum.closed || escapeRoomStateEnum.finished:
                    setSession("")
                    setOpenOpenSnackbar(true)
                    break;
                default:
                    console.log("Lobby is in an unknown state");
            }
        }
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

const isEscapeRoomOpen = (escape_room_state: EscapeRoomStateEnum) => {
    return escape_room_state === escapeRoomStateEnum.open;
}

export default StudentJoin;