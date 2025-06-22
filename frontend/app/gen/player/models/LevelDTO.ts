// version: 1.0
import type { RiddleDTO } from './RiddleDTO.ts'
import type { SceneDTO } from './SceneDTO.ts'

/**
 * @description The current level of an escape-room instance
 */
export type LevelDTO = {
  /**
   * @description The unique ID of the escape room template
   * @type string | undefined
   */
  template_id?: string
  /**
   * @description The unique ID of the escape room level
   * @type string | undefined
   */
  level_id?: string
  /**
   * @description The sequence number of the level
   * @type integer | undefined
   */
  level_sequence?: number
  /**
   * @description List of scenes in the level
   * @type array | undefined
   */
  scenes?: SceneDTO[]
  /**
   * @type object | undefined
   */
  riddle?: RiddleDTO
}