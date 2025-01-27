import {useMutation, useQuery} from "@tanstack/react-query";
import {changeRoomState, getAllRooms} from "@/app/api/lectorPortal/portal";
import {RoomState} from "@/app/enums/RoomState";
import {SetStateAction} from "react";
import {RoomCardState} from "@/app/lector-portal/dashboard/_components/RoomCard";

export const useGetEscapeRooms = () => {
    return useQuery({
            queryKey: ["lectorPortal"],
            queryFn: () => getAllRooms(),
            retry: false
        }
    );
}

export const useChangeRoomState = (id: number, setRoomInfo: (roomInfo: SetStateAction<RoomCardState>) => void, time?: number) => {
    return useMutation({
            mutationFn: (newStatus: RoomState) => changeRoomState(newStatus, id, time),
            onSuccess: (data, newStatus) => {
                console.log("Data", data)
                console.log("NewStatus", newStatus)
                if (newStatus == RoomState.JOINABLE) setRoomInfo((prev) => ({...prev, ID: data}));
                setRoomInfo((prev) => ({...prev, Status: newStatus}));
            }
        }
    );
}