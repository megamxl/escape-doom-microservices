'use client'

import React, {useState} from 'react';
import {Alert, Button, Chip, Fab, Paper, Snackbar, Stack, TextField, Typography} from "@mui/material";
import {
    EscapeRoomStateEnum,
    SessionResponse,
    useAddERTagHook,
    useDeleteERTagHook,
    useToggleERInstanceStateHook
} from "@/app/gen/session";
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import OpenInBrowserIcon from '@mui/icons-material/OpenInBrowser';
import CloseIcon from '@mui/icons-material/Close';
import EditIcon from '@mui/icons-material/Edit';
import SessionStateDisplay from "@/app/lector-portal/dashboard/_components/SessionStateDisplay.tsx";
import {useRouter} from "next/navigation";
import {GAME_SESSION_APP_PATHS, LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";
import {Leaderboard} from '@mui/icons-material';

type SessionCardProps = {
    session: SessionResponse,
    templateName: string,
    onSessionUpdate: (s: SessionResponse) => void
}

const SessionCard = ({session, templateName, onSessionUpdate}: SessionCardProps) => {
    const [cardInfo, setCardInfo] = useState<SessionResponse>(session);

    const [newTag, setNewTag] = useState('');
    const {mutate: addTag} = useAddERTagHook();
    const {mutate: removeTag} = useDeleteERTagHook();
    const [showInput, setShowInput] = useState(false);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);


    const router = useRouter()
    const {mutate} = useToggleERInstanceStateHook()

    const handleSessionStateChange = (state: EscapeRoomStateEnum) => {
        mutate({
            //@ts-expect-error Könnte in der Theorie leer sein, sollte aber nie der Fall sein
            state: state.toUpperCase(),
            session_id: cardInfo.session_id!
        }, {
            onSuccess: () => {
                const updated = {...cardInfo, state: state}
                setCardInfo(updated)
                onSessionUpdate(updated)
            },
            onError: (error) => {
                setErrorMessage(error.message)
            }
        })
    }

    const redirectToEdit = () => {
        router.push(`${LECTOR_PORTAL_APP_PATHS.EDITOR}/${cardInfo.template_id}`,)
        router.refresh()
    }

    const handleAddTag = () => {
        if (!newTag) return;

        if (cardInfo.tags?.includes(newTag)) {
            setErrorMessage(`Tag "${newTag}" already exists.`);
            setNewTag('');
            return;
        }

        addTag(
            {
                session_id: cardInfo.session_id!,
                tag_name: newTag,
            },
            {
                onSuccess: (updated) => {
                    setCardInfo(updated);
                    onSessionUpdate(updated);
                    setNewTag('');
                },
                onError: () => setErrorMessage("Failed to add tag."),
            }
        );
    };

    const handleDeleteTag = (tag: string) => {
        removeTag({
                session_id: cardInfo.session_id!,
                tag_name: tag
            },
            {
                onSuccess: (updated) => {
                    setCardInfo(updated);
                    onSessionUpdate(updated);
                },
                onError: (error) => setErrorMessage(error.message)
            }
        );
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
                <Fab style={{position: "absolute", top: '10px', right: '10px'}} size="small" color="primary"
                     aria-label="change-state"
                     href={`${GAME_SESSION_APP_PATHS.LEADERBOARD}/${cardInfo.room_pin}`}
                     target="_blank"
                >
                    <Leaderboard/>
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
                    <Typography textAlign={"center"} variant="subtitle1" color="textSecondary">
                        Template: {templateName}
                    </Typography>
                </Stack>

            </Paper>

            <Stack direction="row" justifyContent="space-between" alignItems="center" mt={1} flexWrap="wrap" rowGap={1}>

                <SessionStateDisplay state={cardInfo.state!}/>

                <Stack direction="row-reverse" spacing={1} flexWrap="wrap-reverse" justifyContent="space-between"
                       alignItems="center" rowGap={1}>

                    {cardInfo.tags?.map((tag) => (
                        <Chip
                            key={tag}
                            label={tag}
                            onDelete={() => handleDeleteTag(tag)}
                            color="primary"
                        />
                    ))}

                    {!showInput ? (
                        <Typography
                            sx={{
                                fontSize: '0.8rem',
                                cursor: 'pointer',
                                color: 'primary.main',
                            }}
                            onClick={() => setShowInput(true)}
                        >
                            + Add Tags
                        </Typography>
                    ) : (
                        <TextField
                            autoFocus
                            size="small"
                            variant="outlined"
                            placeholder="Type and press Enter"
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
                            sx={{input: {color: 'primary.main'}}}
                        />
                    )}
                </Stack>
            </Stack>
            <Snackbar open={!!errorMessage} autoHideDuration={5000} onClose={() => setErrorMessage(null)}>
                <Alert onClose={() => setErrorMessage(null)} severity="error" variant="filled" sx={{width: "100%"}}>
                    {errorMessage}
                </Alert>
            </Snackbar>
        </div>
    );
};

export default SessionCard;