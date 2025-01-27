import {CompileStatus} from "@/app/enums/CompileStatus";

export type CodeExecResponse = {
    status: CompileStatus,
    output: string
}