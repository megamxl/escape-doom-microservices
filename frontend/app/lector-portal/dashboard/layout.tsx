"use client"

import React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import {LECTOR_SESSION_STORAGE_STRING, useLectorToken} from "@/app/utils/lector-token-handler";
import {removeSessionStorageItem} from "@/app/utils/session-storage-handler";
import {redirect} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths";

const TopAppBar = ({children}: Readonly<{ children: React.ReactNode;}>) => {

    const [token, setToken] = useLectorToken();

    const handleLogout = () => {
        removeSessionStorageItem(LECTOR_SESSION_STORAGE_STRING)
        redirect(LECTOR_PORTAL_APP_PATHS.LOGIN)
    }

    return (
        <>
            <Box sx={{flexGrow: 1}}>
                <AppBar position="static">
                    <Toolbar>
                        <IconButton
                            size="large"
                            edge="start"
                            color="inherit"
                            aria-label="menu"
                            sx={{mr: 2}}
                        >
                            <MenuIcon/>
                        </IconButton>
                        <Typography variant="h6" component="div" sx={{flexGrow: 1}}>
                            Escape Doom
                        </Typography>
                        <Button
                            color="inherit"
                            onClick={handleLogout}
                        >
                            {token !== "" && "Logout"}
                        </Button>
                    </Toolbar>
                </AppBar>
            </Box>
            {children}
        </>
    );
};

export default TopAppBar;