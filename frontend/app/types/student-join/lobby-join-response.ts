import {RoomState} from "@/app/enums/RoomState";


export type LobbyJoinResponse = {
    name: string,
    sessionId: string,
    state: RoomState
}