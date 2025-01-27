import {StageScene} from "@/app/types/game-session/StageScene";

export function parseStage(data: string[] | undefined): StageScene | null {
    try {

        if (data && data.length > 0) {
            if (data) {
                return JSON.parse(data[0])[0];
            }
        }
        return null;
    } catch (error) {
        console.error('Error parsing stage data:', error);
        return null;
    }
}