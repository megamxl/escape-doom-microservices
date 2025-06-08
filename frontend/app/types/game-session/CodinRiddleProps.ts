import {CompileStatus} from "@/app/enums/CompileStatus";

export type CodingRiddleProps = {
    status: CompileStatus,
    output: string
}