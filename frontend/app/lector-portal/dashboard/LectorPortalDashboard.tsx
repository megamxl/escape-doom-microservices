'use client'

import React, {useEffect, useState} from 'react';
import {
    Box,
    Button,
    Divider,
    Grid2,
    InputBase,
    Paper,
    Stack,
    ToggleButton,
    ToggleButtonGroup,
    Typography
} from "@mui/material";
import RoomCardSkeleton from "@/app/lector-portal/dashboard/_components/RoomCardSkeleton";
import SearchIcon from '@mui/icons-material/Search';
import CheckIcon from '@mui/icons-material/Check';
import {redirect} from "next/navigation";
import {useGetAllTemplatesHook} from "@/app/gen/data";
import IconButton from "@mui/material/IconButton";
import {escapeRoomStateEnum} from "@/app/gen/session";

const LectorPortalDashboard = () => {

    const {data, isPending, isError, error} = useGetAllTemplatesHook()
    const [selected, setSelected] = useState([true, false, false, false]);

    const handleFilterSelection = (idx: number) => {
        let curSelection = selected.slice()
        curSelection[idx] = !curSelection[idx]
        setSelected(curSelection)
    }

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
                                Object.values(escapeRoomStateEnum).map((state, index) => {
                                    return (
                                        <Button
                                            key={state}
                                            size={"small"}
                                            variant={"contained"}
                                            sx={{mr: 1}}
                                            color={"secondary"}
                                            onClick={() => handleFilterSelection(index)}
                                        >
                                            {selected[index] ? <CheckIcon fontSize={"small"}  /> : null }
                                            {state.toUpperCase()}
                                        </Button>
                                    )
                                })
                            }
                            <Button
                                color={"secondary"}
                                onClick={() => setSelected([false, false, false, false])}
                                sx={{fontSize: "0.7rem"}}>
                                Clear all filters
                            </Button>

                        </div>
                        <Paper component={"form"} sx={{ p: '2px 4px', display: 'flex', alignItems: 'center', width: 350}}>
                            <InputBase sx={{ml: 1, flex: 1}} placeholder={"Search by Pin or Tag"} />
                            <IconButton type={"button"} sx={{ p: '10'}}>
                                <SearchIcon />
                            </IconButton>
                        </Paper>

                    </Stack>
                    <Grid2 container spacing={{md: 6, lg: 12}}>
                        {/*@ts-ignore*/}
                        {data ? data.map(({name, topic, time, escapeRoomState, escaproom_id}, index) => (
                            <Grid2 key={index} size={{lg: 6, sm: 12}}>
                                {/*<RoomCard name={name} topic={topic} imgUrl={BackgroundImage.src}*/}
                                {/*          time={time} id={escaproom_id} escapeRoomState={escapeRoomState}*/}
                                {/*/>*/}
                            </Grid2>
                        )) :
                            [...Array(1)].map((_, index) => (
                            <Grid2 key={index} size={{lg: 6, sm: 12}}>
                                <RoomCardSkeleton key={index}/>
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