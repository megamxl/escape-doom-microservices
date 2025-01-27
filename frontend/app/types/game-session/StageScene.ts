import {StageNode} from "@/app/types/game-session/StageNode";

export type StageScene = {
    id: number,
    name: string,
    bgImg: string,
    nodes: StageNode[]
}