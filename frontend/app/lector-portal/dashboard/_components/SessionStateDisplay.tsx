import React from 'react';
import {EscapeRoomState} from "@/app/gen/player";
import {Box, Stack, Typography} from "@mui/material";
import TripOriginIcon from '@mui/icons-material/TripOrigin';
import stateColorMapping from '@/app/constants/stateColorMapping';

const SessionStateDisplay = ({state}: {state: EscapeRoomState }) => {

    const color = stateColorMapping(state);

    return (
        <Stack direction="row" spacing={1} alignItems={"center"}>
            <Typography variant={"caption"}>
                <Box component={"span"} display={"inline"}>
                    Status: <span> {state.toUpperCase()} </span>
                </Box>
            </Typography>
            <TripOriginIcon style={{ color: color, verticalAlign: 'middle'}} />
        </Stack>
    );
};

export default SessionStateDisplay;