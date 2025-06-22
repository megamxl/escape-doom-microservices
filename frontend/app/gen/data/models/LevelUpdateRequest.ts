// version: 0.0.1
import type { RiddleDTO } from './RiddleDTO.ts'

/**
 * @description The payload for updating a level
 */
export type LevelUpdateRequest = {
  /**
   * @description The name of the level
   * @type string | undefined
   */
  name?: string
  /**
   * @description The sequence number of the level
   * @type integer | undefined
   */
  level_sequence?: number
  /**
   * @type object | undefined
   */
  riddle?: RiddleDTO
}