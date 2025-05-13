import React from 'react';
import {ConsoleNodeSpecificsDTO} from "@/app/gen/data";
import {Stack, Typography} from "@mui/material";

const DetailNodeDetails = ({specifics, desc}: { specifics: ConsoleNodeSpecificsDTO, desc: string }) => {
    const {image_uri} = specifics
    return (
        <Stack direction="row" spacing={3}>
            {/* eslint-disable-next-line @next/next/no-img-element */}
            <img alt={"Node image"} src={image_uri} width="40%" />
            <Typography> {desc} </Typography>
        </Stack>
    )
}

export default DetailNodeDetails;