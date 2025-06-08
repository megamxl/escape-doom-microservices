'use client'

import React from 'react';
import {Stack, TextField} from "@mui/material";
import {
    NodeSpecificProps
} from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/BasicNodeForm.tsx";
import {ConsoleNodeSpecificsDTO} from "@/app/gen/data";

const ConsoleNodeForm = ({nodeSpecifics, setter}: NodeSpecificProps) => {
    const consoleSpecifics = nodeSpecifics as ConsoleNodeSpecificsDTO
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
                value={consoleSpecifics.return_description}
            />
            <TextField
                onChange={(e) => {
                    setter(prev => ({...prev, constraints: e.target.value}))
                }}
                required
                label="Constraints"
                variant="outlined"
                rows="3"
                value={consoleSpecifics.constraints}
            />
            <TextField
                onChange={(e) => {
                    setter(prev => ({...prev, example: e.target.value}))
                }}
                label="Example"
                required
                variant="outlined"
                rows="3"
                value={consoleSpecifics.example}
            />
        </Stack>
    );
};

export default ConsoleNodeForm;