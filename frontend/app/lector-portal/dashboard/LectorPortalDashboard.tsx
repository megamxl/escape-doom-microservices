'use client'

import React, {useEffect, useState} from 'react';
import {Box, Button, Divider, Grid2, InputBase, Paper, Stack, Typography} from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import CheckIcon from '@mui/icons-material/Check';
import {redirect} from "next/navigation";
import IconButton from "@mui/material/IconButton";
import {EscapeRoomState, escapeRoomStateEnum, useGetERHistoryHook} from "@/app/gen/session";
import SessionCard from "@/app/lector-portal/dashboard/_components/SessionCard.tsx";
import SessionCardSkeleton from "@/app/lector-portal/dashboard/_components/SessionCardSkeleton.tsx";

const LectorPortalDashboard = () => {

    const {data, isPending, isError, error} = useGetERHistoryHook()
    const [selected, setSelected] = useState(new Map([
        ['open', true],
        ['started', false],
        ['finished', false],
        ['closed', false]
    ]));

    const handleFilterSelection = (state: EscapeRoomState) => {
        setSelected(prev => {
            const newSelection = new Map(prev)
            newSelection.set(state, !prev.get(state))
            return newSelection
        })
    }

    const clearFilters = () => {
        setSelected(prev => {
            const newSelection = new Map();
            for (let key of prev.keys()) {
                newSelection.set(key, false);
            }
            return newSelection;
        });
    }

    useEffect(() => {
        console.log(`Received templates: ${data}`)
        console.log(data)
    }, [data]);

    useEffect(() => {
        // @ts-ignore
        if (error?.status === 403 && !isPending) {
            console.error("Error", error)
            redirect("/lector-portal/login")
        }
    }, [isError]);

    return (
        <>
            <Box width="70vw" margin="auto" mt={6}>
                <Stack gap={3}>
                    <Stack direction="row" alignItems="center">
                        <Typography fontSize="16" fontWeight="bold" mr={2}> Your Escape Rooms </Typography>
                        <Divider sx={{flexGrow: 1, borderBottomWidth: 3}} orientation="horizontal"/>
                    </Stack>
                    <Stack direction="row" gap="4" justifyContent={"space-between"}>
                        <div>
                            {
                                Object.values(escapeRoomStateEnum).map((state) => {
                                    return (
                                        <Button
                                            key={state}
                                            size={"small"}
                                            variant={"contained"}
                                            sx={{mr: 1}}
                                            color={"secondary"}
                                            onClick={() => handleFilterSelection(state)}
                                        >
                                            {selected.get(state) ? <CheckIcon fontSize={"small"}/> : null}
                                            {state.toUpperCase()}
                                        </Button>
                                    )
                                })
                            }
                            <Button
                                color={"secondary"}
                                onClick={clearFilters}
                                sx={{fontSize: "0.7rem"}}>
                                Clear all filters
                            </Button>

                        </div>
                        <Paper component={"form"}
                               sx={{p: '2px 4px', display: 'flex', alignItems: 'center', width: 350}}>
                            <InputBase sx={{ml: 1, flex: 1}} placeholder={"Search by Pin or Tag"}/>
                            <IconButton type={"button"} sx={{p: '10'}}>
                                <SearchIcon/>
                            </IconButton>
                        </Paper>

                    </Stack>
                    <Grid2 container spacing={3}>
                        {/*@ts-ignore*/}
                        {data ? data.map((session, index) => {
                                if (selected.get(session.state!)) {
                                    return (
                                        <Grid2 key={index} size={{lg: 4, md: 6, sm: 12}}>
                                            <SessionCard session={session}/>
                                        </Grid2>
                                    )
                                }
                            }) :
                            [...Array(6)].map((_, index) => (
                                <Grid2 key={index} size={{lg: 4, md: 6, sm: 12}}>
                                    <SessionCardSkeleton/>
                                </Grid2>
                            ))
                        }
                    </Grid2>
                </Stack>
            </Box>
        </>
    );
};

export default LectorPortalDashboard;