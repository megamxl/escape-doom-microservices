'use client'

import { useEffect } from "react";
import {Button} from "@mui/material";
import {useCreateTemplateHook} from "@/app/gen";

export default function Test() {

    const templateHook = useCreateTemplateHook();

    useEffect(() => {
        console.log("Data:", templateHook.data)
    }, [templateHook.data]);

    const handleClick = async () => {
        console.log("clicked")
        templateHook.mutate({ data: { name:  "SDE24", description: "Cäsar´s Rätsel"}}); //
    }

    return (
        <>
            <Button onClick={handleClick}> Send stuff </Button>
        </>
    );
}
