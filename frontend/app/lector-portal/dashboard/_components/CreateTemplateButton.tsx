import React, {FormEvent, useState} from 'react';
import {
    Alert,
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Snackbar,
    Stack,
    TextField
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import {type CreateTemplateMutationRequest, useCreateTemplateHook} from "@/app/gen/data";
import {green, red} from "@mui/material/colors";
import {useRouter} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";

const CreateTemplateButton = () => {

    const {mutate} = useCreateTemplateHook()
    const [open, setOpen] = useState(false);
    const [isError, setIsError] = useState(false);
    const [templateRequest, setTemplateRequest] = useState<CreateTemplateMutationRequest>()

    const router = useRouter()

    const openDialog = () => setOpen(true)
    const closeDialog = () => setOpen(false)

    const createNewTemplate = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        mutate({
                data: templateRequest
            }, {
                onSuccess: (newTemplate) => {
                    router.push(`${LECTOR_PORTAL_APP_PATHS.EDITOR}/${newTemplate.template_id}`)
                },
                onError: (errorMsg) => {
                    setIsError(true)
                }
            }
        )
        closeDialog()
    }

    return (
        <>
            <Button
                onClick={openDialog}
                startIcon={<EditIcon/>}
                variant={"contained"}
                color={"secondary"}
            >
                Create new template
            </Button>
            <Dialog open={open} slotProps={{
                paper: {component: 'form', onSubmit: createNewTemplate}
            }} sx={{'& .MuiDialog-paper': {width: '80%', maxHeight: 435}}}>
                <DialogTitle> New Template </DialogTitle>
                <DialogContent dividers>
                    <Stack spacing={2}>
                        <TextField required label={"Name"} onChange={event => {
                            setTemplateRequest({...templateRequest, name: event.target.value})
                        }}/>
                        <TextField required label={"Description"} onChange={event => {
                            setTemplateRequest({...templateRequest, description: event.target.value})
                        }}/>
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button variant={"contained"} onClick={closeDialog} sx={{ backgroundColor: red[300] }}> Cancel </Button>
                    <Button variant={"contained"} type={"submit"} sx={{backgroundColor: green[300]}}> Create </Button>
                </DialogActions>
            </Dialog>

            <Snackbar open={isError} onClose={() =>  setIsError(false)}>
                <Alert severity={"error"}>
                    Creating template failed - Please try again later or contact support
                </Alert>
            </Snackbar>
        </>
    );
};

export default CreateTemplateButton