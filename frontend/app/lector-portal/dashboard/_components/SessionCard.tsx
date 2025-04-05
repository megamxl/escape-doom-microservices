'use client'

import React from 'react';
import {Button, Chip, Fab, Paper, Stack, Typography} from "@mui/material";
import {EscapeRoomSessionResponse} from "@/app/gen/session";
import SettingsIcon from '@mui/icons-material/Settings';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import OpenInBrowserIcon from '@mui/icons-material/OpenInBrowser';
import CloseIcon from '@mui/icons-material/Close';
import SessionStateDisplay from "@/app/lector-portal/dashboard/_components/SessionStateDisplay.tsx";

const SessionCard = ({session}: { session: EscapeRoomSessionResponse }) => {
    const {state, room_pin, tags} = session;

    const handleSessionStateChange = () => {
        console.log("I have been clicked")
    }

    const handleDeleteTag = (tag: string) => {
        console.log("Will try to delete tag " + tag)
    }

    return (
        <div>
            <Paper style={{
                minHeight: '200px',
                position: 'relative',
                display: 'flex',
                flexDirection: 'column',
                marginBottom: '0.5rem'
            }}>
                <Stack spacing={1} direction={"row"} justifyContent={"right"}>
                    <Button startIcon={<OpenInBrowserIcon/>}> Open </Button>
                    <Button startIcon={<PlayArrowIcon/>}> Start </Button>
                    <Button startIcon={<CloseIcon/>}> Close </Button>
                </Stack>
                {/*<Fab style={{position: "absolute", top: '10px', right: '10px'}} size="small" color="primary"*/}
                {/*     aria-label="change-state" onClick={handleSessionStateChange}>*/}
                {/*    <SettingsIcon/>*/}
                {/*</Fab>*/}
                <Stack style={{flexGrow: 1, height: '100%'}} justifyContent={"center"} alignItems={"center"}>
                    <Typography textAlign={"center"} variant="h4"> PIN: {room_pin} </Typography>
                </Stack>
            </Paper>
            <Stack direction={"row"} justifyContent={"space-between"}>
                <SessionStateDisplay state={state!}/>
                <Stack direction={"row"} spacing={2}>
                    <Button size={"small"}> + Add Tags </Button>
                    {tags?.map(tag => {
                        return (
                            <Chip key={tag} label={tag} variant={"outlined"} onDelete={() => handleDeleteTag(tag)}/>
                        )
                    })}
                </Stack>
            </Stack>
        </div>
    );
};

export default SessionCard;