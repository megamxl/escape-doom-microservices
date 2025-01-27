'use client'

import React, {ChangeEvent, useState} from 'react';
import {Alert, Avatar, Box, Button, Container, Snackbar, TextField, Typography} from "@mui/material";
import {LockOutlined} from "@mui/icons-material";
import {useLectorToken} from "@/app/utils/lector-token-handler";
import {AuthCreds} from "@/app/api/lectorPortal/login";
import {useAuthentication} from "@/app/hooks/login/useAuthentication";
import {redirect, RedirectType} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths";

const Login = () => {

    const [open, setOpen] = useState(false)
    const [loginData, setLoginData] = useState<AuthCreds>({email: "", password: ""})
    const [token, setToken] = useLectorToken()

    const {refetch} = useAuthentication(loginData)

    async function login (e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault()
        const refetchResponse = await refetch();
        if (!refetchResponse.isError) {
            // @ts-ignore
            setToken(refetchResponse.data?.token)
            redirect(LECTOR_PORTAL_APP_PATHS.DASHBOARD, RedirectType.push)
        } else {
            setOpen(true)
        }
    }

    const handleClose = () => setOpen(false)

    const onChangePassword = (e: ChangeEvent<HTMLInputElement>) => {
        setLoginData({...loginData, password: e.target.value});
    };

    const onChangeEmail = (e: ChangeEvent<HTMLInputElement>) => {
        setLoginData({...loginData, email: e.target.value})
    };

    return (
        <>
            <Container maxWidth="xs">
                <Box
                    sx={{
                        mt: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center'
                    }}
                >
                    <Avatar sx={{m: 1}}>
                        <LockOutlined/>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Log In!
                    </Typography>
                    <Box component="form" onSubmit={login} noValidate mt={1}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email Address"
                            name="email"
                            onChange={onChangeEmail}
                            autoComplete="email"
                            autoFocus
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            onChange={onChangePassword}
                            autoComplete="current-password"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant={"contained"}
                            sx={{mt: 3, mb: 2}}
                        >
                            Log In
                        </Button>
                    </Box>
                </Box>
            </Container>
            <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
                <Alert onClose={handleClose} severity="error" sx={{width: '100%'}}>
                    Logging in didn't work! Make sure you use the correct credentials
                </Alert>
            </Snackbar>

        </>
    );
};

export default Login;