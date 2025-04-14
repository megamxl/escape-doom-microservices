'use client'

import React, {useState} from 'react';
import {Alert, Button, Chip, Fab, Paper, Snackbar, Stack, TextField, Typography} from "@mui/material";
import {EscapeRoomSessionResponse, EscapeRoomStateEnum, useToggleERInstanceStateHook} from "@/app/gen/session";
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import OpenInBrowserIcon from '@mui/icons-material/OpenInBrowser';
import CloseIcon from '@mui/icons-material/Close';
import EditIcon from '@mui/icons-material/Edit';
import SessionStateDisplay from "@/app/lector-portal/dashboard/_components/SessionStateDisplay.tsx";
import {useRouter} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";
import {useAddTagToSession} from "@/app/hooks/lector-portal/useAddTagToSession.ts";
import {useRemoveTagFromSession} from "@/app/hooks/lector-portal/useRemoveTagFromSession.ts";

type SessionCardProps = {
    session: EscapeRoomSessionResponse,
    onSessionUpdate: (s: EscapeRoomSessionResponse) => void
}

const SessionCard = ({session, onSessionUpdate}: SessionCardProps) => {
    const [cardInfo, setCardInfo] = useState<EscapeRoomSessionResponse>(session);
    const [open, setOpen] = React.useState(false);

    const [newTag, setNewTag] = useState('');
    const {mutate: addTag} = useAddTagToSession();
    const {mutate: removeTag} = useRemoveTagFromSession();
    const [showInput, setShowInput] = useState(false);

    const router = useRouter()
    const {mutate} = useToggleERInstanceStateHook()

    const handleSessionStateChange = (state: EscapeRoomStateEnum) => {
        mutate({
            // @ts-ignore
            state: state.toUpperCase(),
            escape_room_session_id: cardInfo.escape_room_session_id!
        }, {
            onSuccess: () => {
                const updated = {...cardInfo, state: state}
                setCardInfo(updated)
                onSessionUpdate(updated)
            },
            onError: () => {
                setOpen(true)
            }
        })
    }

    const redirectToEdit = () => {
        router.push(`${LECTOR_PORTAL_APP_PATHS.EDITOR}/${cardInfo.escape_room_template_id}`)
    }

    const handleAddTag = () => {
        if (!newTag) {
            return;
        }

        addTag(
            { sessionId: cardInfo.escape_room_session_id!, tag: newTag },
            {
                onSuccess: (updated) => {
                    setCardInfo(updated);
                    onSessionUpdate(updated);
                    setNewTag('');
                },
                onError: (error) => {
                    setOpen(true);
                }
            }
        );
    };

    const handleDeleteTag = (tag: string) => {
        removeTag({sessionId: cardInfo.escape_room_session_id!, tag}, {
            onSuccess: (updated) => {
                setCardInfo(updated);
                onSessionUpdate(updated);
            },
            onError: () => setOpen(true)
        });
    };

    return (
        <div>
            <Paper style={{
                minHeight: '200px',
                position: 'relative',
                display: 'flex',
                flexDirection: 'column',
                marginBottom: '0.5rem'
            }}>
                <Fab style={{position: "absolute", top: '10px', left: '10px'}} size="small" color="primary"
                     aria-label="change-state" onClick={redirectToEdit}>
                    <EditIcon/>
                </Fab>
                <Stack style={{position: "absolute", bottom: '5px', left: "50%", transform: "translate(-50%, 0)"}}
                       spacing={1} direction={"row"}>
                    <Button size={"small"} startIcon={<OpenInBrowserIcon/>}
                            onClick={() => handleSessionStateChange("open")}> Open </Button>
                    <Button size={"small"} startIcon={<PlayArrowIcon/>}
                            onClick={() => handleSessionStateChange("started")}> Start </Button>
                    <Button size={"small"} startIcon={<CloseIcon/>}
                            onClick={() => handleSessionStateChange("closed")}> Close </Button>
                </Stack>
                <Stack style={{flexGrow: 1, height: '100%'}} justifyContent={"center"} alignItems={"center"}>
                    <Typography textAlign={"center"} variant="h4"> PIN: {cardInfo.room_pin} </Typography>
                </Stack>
            </Paper>

            <Stack direction="row" justifyContent="space-between" alignItems="center" mt={1} flexWrap="wrap">
                <SessionStateDisplay state={cardInfo.state!}/>
                <Stack direction="row" spacing={1} alignItems="center" flexWrap="wrap">
                    {cardInfo.tags?.map(tag => (
                        <Chip key={tag} label={tag} onDelete={() => handleDeleteTag(tag)} color="primary"/>
                    ))}

                    {!showInput ? (
                        <Typography
                            sx={{cursor: 'pointer', color: 'primary.main'}}
                            onClick={() => setShowInput(true)}
                        >
                            + Add Tags
                        </Typography>
                    ) : (
                        <TextField
                            autoFocus
                            size="small"
                            variant="outlined"
                            value={newTag}
                            onChange={(e) => setNewTag(e.target.value)}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    handleAddTag();
                                } else if (e.key === 'Escape') {
                                    setShowInput(false);
                                    setNewTag('');
                                }
                            }}
                            onBlur={() => {
                                setShowInput(false);
                                setNewTag('');
                            }}
                            sx={{
                                input: {color: 'black'},
                                '& .MuiOutlinedInput-root': {
                                    '& fieldset': {
                                        borderColor: 'primary.main',
                                    },
                                    '&:hover fieldset': {
                                        borderColor: 'primary.dark',
                                    },
                                    '&.Mui-focused fieldset': {
                                        borderColor: 'primary.main',
                                    },
                                },
                                ml: 1
                            }}
                        />
                    )}
                </Stack>
            </Stack>

            <Snackbar open={open} autoHideDuration={5000} onClose={() => setOpen(false)}>
                <Alert onClose={() => setOpen(false)} severity={"error"} variant={"filled"} sx={{width: "100%"}}>
                    You can't do this!
                </Alert>
            </Snackbar>
        </div>
    );
};

export default SessionCard;