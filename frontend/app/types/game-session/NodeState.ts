import {NodeType} from "@/app/types/game-session/NodeType";

export type NodeState = {
    type: NodeType
    pos: { x: number, y: number }
    nodeInfos: object
    codeSetter: React.Dispatch<React.SetStateAction<string>>
}

export type NodeInstance = {
    pos: { x: number, y: number }
    nodeInfos: any
}