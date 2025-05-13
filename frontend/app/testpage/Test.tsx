'use client'

import { useEffect } from "react";
import {Button} from "@mui/material";
import {useCreateTemplateHook} from "@/app/gen/data";
import DnDGridSnap from "@/app/_components/DnDGridSnap.tsx";

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
            <DnDGridSnap />
            <Button onClick={handleClick}> Send stuff </Button>
        </>
    );
}
