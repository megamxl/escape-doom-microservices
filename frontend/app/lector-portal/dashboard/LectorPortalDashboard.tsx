'use client'

import React, {useState} from 'react';
import {Box, Button, Divider, Grid2, InputBase, Paper, Stack, Typography} from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import CheckIcon from '@mui/icons-material/Check';
import IconButton from "@mui/material/IconButton";
import {useGetERHistoryHook} from "@/app/gen/session";
import SessionCard from "@/app/lector-portal/dashboard/_components/SessionCard.tsx";
import SessionCardSkeleton from "@/app/lector-portal/dashboard/_components/SessionCardSkeleton.tsx";
import AddSessionFromTemplateCard from "@/app/lector-portal/dashboard/_components/AddSessionFromTemplateCard.tsx";

const LectorPortalDashboard = () => {

    const {data, isPending, isError, error, refetch} = useGetERHistoryHook()
    const [selected, setSelected] = useState(new Map([
        ['open', true],
        ['started', true],
        ['finished', false],
        ['closed', false]
    ]));

    const handleFilterSelection = (state: string) => {
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
                newSelection.set(key, true);
            }
            return newSelection;
        });
    }

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
                                selected.keys().map((state) => {
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
                        <Grid2 size={{lg: 4, md: 6, sm: 12}}>
                            <AddSessionFromTemplateCard onDone={refetch} />
                        </Grid2>
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