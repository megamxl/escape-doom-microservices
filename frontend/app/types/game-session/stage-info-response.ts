import {RoomState} from "@/app/enums/RoomState";
import {StageScene} from "@/app/types/game-session/StageScene";

export type StageInfoResponse = {
    stage: string[],
    state: RoomState,
    roomID: number
}

export type StageInfo = {
    stage: StageScene[],
    state: RoomState,
    roomID: number
}