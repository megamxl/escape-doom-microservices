import {RoomState} from "@/app/enums/RoomState";

export type LobbyStatusResponse = {
    state: RoomState,
    roomID: number
}