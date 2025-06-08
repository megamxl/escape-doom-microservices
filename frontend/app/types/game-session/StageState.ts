import {CodeLanguage} from "@/app/enums/CodeLanguage";
import {StageScene} from "@/app/types/game-session/StageScene";
import {CodingRiddleDTO, RiddleWrapper} from "@/app/gen/player";

export type StageState = {
    riddle: CodingRiddleDTO,
}