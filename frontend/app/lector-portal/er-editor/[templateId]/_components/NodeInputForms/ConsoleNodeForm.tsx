'use client'

import React from 'react';
import {Stack, TextField} from "@mui/material";
import {ConsoleNodeSpecificsDTO} from "@/app/gen/data";
import {NodeDTO} from "@/app/gen/player";

export type ConsoleNodeSpecificProps = {
    nodeSpecifics: ConsoleNodeSpecificsDTO
    setter: React.Dispatch<React.SetStateAction<NodeDTO>>
}

const ConsoleNodeForm = ({nodeSpecifics, setter}: ConsoleNodeSpecificProps) => {
    return (
        <Stack className="w-full" spacing={2}>
            <TextField
                onChange={(e) => {
                    setter(prev => ({...prev, return_description: e.target.value}))
                }}
                required
                label="Return description"
                variant="outlined"
                rows="3"
                value={nodeSpecifics.return_description}
            />
            <TextField
                onChange={(e) => {
                    setter(prev => ({...prev, constraints: e.target.value}))
                }}
                required
                label="Constraints"
                variant="outlined"
                rows="3"
                value={nodeSpecifics.constraints}
            />
            <TextField
                onChange={(e) => {
                    setter(prev => ({...prev, example: e.target.value}))
                }}
                label="Example"
                required
                variant="outlined"
                rows="3"
                value={nodeSpecifics.example}
            />
        </Stack>
    );
};

export default ConsoleNodeForm;