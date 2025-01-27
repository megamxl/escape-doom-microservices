import {CodeLanguage} from "@/app/enums/CodeLanguage";

export type SubmittedCodeBody = {
    playerSessionId: string,
    language: CodeLanguage,
    code: string,
    codeRiddleID: number,
    dateTime: Date
}