import React, {useState} from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogTitle, Paper, TextField, Typography} from "@mui/material";

import AddIcon from '@mui/icons-material/Add';
import {type CreateERInstanceMutationRequest, useCreateERInstanceHook} from "@/app/gen/session";

const AddSessionFromTemplateCard = ({onDone}: {onDone: () => void}) => {

    const {mutate} = useCreateERInstanceHook();
    const [open, setOpen] = useState(false)
    const [newSession, setNewSession] = useState<CreateERInstanceMutationRequest>({
        escape_room_template_id: '',
        play_time: 60
    });

    const openTemplateSelection = () => setOpen(true)

    const handleClose = () => setOpen(false)

    const createNewSession = () => {
        mutate({
            data: newSession
        })
        onDone();
        handleClose();
    }

    return (
        <>

            <Paper onClick={openTemplateSelection} style={{
                height: '200px',
                position: 'relative',
                display: 'flex',
                flexDirection: 'column',
                marginBottom: '0.5rem',
                cursor: 'pointer',
                border: 'dashed gray'
            }}>
                <AddIcon sx={{
                    fontSize: "10rem",
                    position: 'absolute',
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)'
                }}/>
            </Paper>
            <Typography variant={"subtitle1"} align={"center"} > Create new session from template </Typography>
            <Dialog open={open} onClose={() => setOpen(false)}>
                <DialogTitle> Create new Escape Room </DialogTitle>
                <DialogContent>
                    <TextField autoFocus required label="Playtime" variant="outlined" fullWidth type="number" />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} > Cancel </Button>
                    <Button onClick={createNewSession}> Create </Button>
                </DialogActions>
            </Dialog>
        </>
    );
};

export default AddSessionFromTemplateCard;