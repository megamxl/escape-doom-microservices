import {NodeType} from "@/app/types/game-session/NodeType";

export type StageNode = {
    pos: {x: number, y: number}
    type: NodeType,
    nodeInfos: NodeInfo
}

export type NodeInfo = {
    png: string,
    desc: string,
    title: string,
    //TODO: These are all special node types - Maybe make generic container and specify later on
    outputID?: number,
    returnType?: string,
    codeSnipped?: string,
    exampleInput?: string,
    exampleOutput?: string,
    parameterType?: string

}