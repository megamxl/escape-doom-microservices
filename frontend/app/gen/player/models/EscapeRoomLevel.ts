// version: 1.0
import type { Scene } from './Scene.ts'

/**
 * @description The current level of an escape-room instance
 */
export type EscapeRoomLevel = {
  /**
   * @type array | undefined
   */
  scenes?: Scene[]
  /**
   * @description The code snippet of the level
   * @type string | undefined
   */
  code_snippet?: string
  /**
   * @description The sequence number of the level
   * @type number | undefined, integer
   */
  level_sequence?: number
}