'use client'

import { useEffect } from "react";
import {Button} from "@mui/material";
import {useGetEscapeRoomSessionsHook, useGetFullExportRoomPinHook} from "@/app/gen";
import {useGetStageInformation} from "@/app/hooks/game-session/useGetStageInformation";

export default function Test() {

    const {data, refetch} = useGetEscapeRoomSessionsHook();

    useEffect(() => {
        console.log(data)
    }, [data]);

    const handleClick = async () => {
        console.log("clicked")
        await refetch()
        console.log("re-fetched")
        console.log(data)
    }

    return (
        <>
            <Button onClick={handleClick}> Send stuff </Button>
        </>
    );
}
