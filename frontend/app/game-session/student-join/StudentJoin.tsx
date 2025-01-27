'use client'

import React, {ChangeEvent, FormEvent, useState} from 'react';
import {Alert, Button, Card, CardContent, Grid2, Snackbar, Stack, TextField, Typography} from "@mui/material";
import BackgroundImage from '@/public/images/StudentJoin.jpg'
import {common} from '@mui/material/colors';
import {useLobbyJoin} from "@/app/hooks/student-join/useLobbyJoin";
import {redirect} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths";
import {RoomState} from "@/app/enums/RoomState";
import {useSession} from "@/app/utils/game-session-handler";

const StudentJoin = () => {

    const [roomPin, setRoomPin] = useState('');
    const [openSnackbar, setOpenOpenSnackbar] = useState(false);

    const [session, setSession] = useSession();
    const {refetch} = useLobbyJoin(roomPin);

    const handleUserInput = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setRoomPin(e.target.value);
    }

    const sendID = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log("Trying to get lobby of id: " + roomPin + "Current session: ", session)
        const {data, isError, error} = await refetch();

        if (session && data?.state == RoomState.PLAYING) redirect(`${GAME_SESSION_APP_PATHS.SESSION}/${session}`)

        if (isError) {
            setOpenOpenSnackbar(true)
            console.error(error)
        } else if (data) {
            const responseSessionID = data.sessionId

            console.log(data)

            switch (data.state) {
                case RoomState.PLAYING:
                    setSession(responseSessionID)
                    redirect(`${GAME_SESSION_APP_PATHS.SESSION}/${responseSessionID}`)
                    break;
                case RoomState.JOINABLE:
                    setSession(responseSessionID)
                    redirect(`${GAME_SESSION_APP_PATHS.LOBBY}/${roomPin}`)
                    break;
                case RoomState.STOPPED:
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
                                type="number"
                                id="outlined-basic"
                                label="Room-PIN"
                                variant="outlined"
                                value={roomPin}
                                onChange={handleUserInput}
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