import {CodeLanguage} from "@/app/enums/CodeLanguage";
import {StageScene} from "@/app/types/game-session/StageScene";

export type StageState = {
    language: CodeLanguage
    stageScene?: StageScene
}