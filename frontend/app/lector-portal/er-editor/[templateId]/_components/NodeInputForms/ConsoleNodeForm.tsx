'use client'

import React, {useEffect, useState} from 'react';
import {Stack, TextField} from "@mui/material";
import {ConsoleNodeSpecificsDTO} from "@/app/gen/data";
import {
    NodeSpecificProps
} from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/BasicNodeForm.tsx";

const ConsoleNodeForm = ({nodeSpecifics}: NodeSpecificProps) => {
    const [{return_description, example, constraints}, setConsoleSpecifics] = useState<ConsoleNodeSpecificsDTO>({
        ...nodeSpecifics,
        node_type: "CONSOLE",
    });

    useEffect(() => {
        console.log(return_description, example, constraints)
    }, [return_description, example, constraints]);

    return (
        <Stack className="w-full" spacing={2}>
            <TextField
                onChange={(e) => {
                    setConsoleSpecifics(prev => ({...prev, return_description: e.target.value}))
                }}
                required
                label="Return description"
                variant="outlined"
                rows="3"
                value={return_description}
            />
            <TextField
                onChange={(e) => {
                    setConsoleSpecifics(prev => ({...prev, constraints: e.target.value}))
                }}
                required
                label="Constraints"
                variant="outlined"
                rows="3"
                value={constraints}
            />
            <TextField
                onChange={(e) => {
                    setConsoleSpecifics(prev => ({...prev, example: e.target.value}))
                }}
                label="Example"
                required
                variant="outlined"
                rows="3"
                value={example}
            />
        </Stack>
    );
};

export default ConsoleNodeForm;