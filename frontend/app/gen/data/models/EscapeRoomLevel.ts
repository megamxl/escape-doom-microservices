// version: 0.0.1
import type { Riddle } from './Riddle.ts'
import type { Scene } from './Scene.ts'

/**
 * @description The current level of an escape-room instance
 */
export type EscapeRoomLevel = {
  /**
   * @description The unique ID of the escape room level
   * @type string | undefined
   */
  escape_room_level_id?: string
  /**
   * @description The sequence number of the level
   * @type number | undefined
   */
  sequence?: number
  /**
   * @description List of scenes in the level
   * @type array | undefined
   */
  scenes?: Scene[]
  /**
   * @description List of riddles in the level
   * @type array | undefined
   */
  riddles?: Riddle[]
}