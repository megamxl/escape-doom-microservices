import React, {ChangeEvent, useState} from 'react';
import {
    Alert,
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    InputAdornment,
    MenuItem,
    Paper, Snackbar,
    Stack,
    TextField,
    Typography
} from "@mui/material";

import AddIcon from '@mui/icons-material/Add';
import {
    type CreateERInstanceMutationRequest,
    EscapeRoomSessionResponse,
    useCreateERInstanceHook
} from "@/app/gen/session";
import {useGetAllTemplatesHook} from "@/app/gen/data";
import green from '@mui/material/colors/green';
import {red} from "@mui/material/colors";

type AddSessionCardProps = {
    onDone: (newSession: EscapeRoomSessionResponse) => void;
}

const AddSessionFromTemplateCard = ({onDone}: AddSessionCardProps) => {

    const {mutate} = useCreateERInstanceHook();
    const {data: allTemplates, isError} = useGetAllTemplatesHook();

    const [open, setOpen] = useState(false)
    const [newSession, setNewSession] = useState<CreateERInstanceMutationRequest>({
        escape_room_template_id: '',
        play_time: 60
    });

    const handleSelection = (event: ChangeEvent<HTMLInputElement>) => {
        setNewSession({...newSession, escape_room_template_id: event.target.value})
    }

    const setNewTime = (event: ChangeEvent<HTMLInputElement>) => {
        setNewSession({...newSession, play_time: Number(event.target.value)})
    }

    const openTemplateSelection = () => setOpen(true)

    const handleClose = () => setOpen(false)

    const createNewSession = () => {
        mutate(
            {data: newSession},
            {
                onSuccess: (created) => {
                    onDone(created)
                    handleClose()
                },
                onError: (e) => {
                    console.error(e)
                }
            }
        )
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
            <Typography variant={"subtitle1"} align={"center"}> Create new session from template </Typography>
            <Dialog
                open={open}
                sx={{'& .MuiDialog-paper': {width: '80%', maxHeight: 435}}}
                slotProps={{ paper: {component: 'form', onSubmit: createNewSession} }}
            >
                <DialogTitle> Create new Escape Room session </DialogTitle>
                <DialogContent dividers>
                    <Stack spacing={2}>
                        <TextField
                            autoFocus
                            required
                            label="Playtime"
                            variant="outlined"
                            fullWidth
                            type="number"
                            helperText={isError ? 'Only times between 15 - 120 are allowed' : ''}
                            onChange={setNewTime}
                            value={newSession.play_time}
                            slotProps={{
                                htmlInput: {
                                    min: 15,
                                    max: 120
                                },
                                input: {
                                    endAdornment: <InputAdornment position="end">minutes</InputAdornment>,
                                },
                            }}
                        />
                        <TextField
                            select
                            required
                            label="Template"
                            value={newSession.escape_room_template_id}
                            onChange={handleSelection}
                        >
                            {
                                allTemplates ? allTemplates.map(template => (
                                    <MenuItem key={template.template_id}
                                              value={template.template_id}> {template.name} </MenuItem>
                                )) : null
                            }
                        </TextField>
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button variant={"contained"} onClick={handleClose} sx={{ backgroundColor: red[300] }}> Cancel </Button>
                    <Button variant={"contained"} type={"submit"} sx={{ backgroundColor: green[300] }}> Create </Button>
                </DialogActions>
            </Dialog>
            <Snackbar
                open={isError}
                autoHideDuration={5000}
            >
                <Alert
                    severity={'error'}
                    variant={'filled'}
                    sx={{ width: '100%' }}
                >
                    Session could not be created - Please try again later or call for support
                </Alert>
            </Snackbar>
        </>
    );
};

export default AddSessionFromTemplateCard;