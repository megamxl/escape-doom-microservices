// version: 1.0
import type { CodingLanguage } from './CodingLanguage.ts'

/**
 * @description Base schema for a riddle
 */
export type RiddleDTO = {
  /**
   * @description The unique ID of the riddle
   * @type string | undefined
   */
  riddle_id?: string
  /**
   * @description The ID of the escape room level that contains this riddle
   * @type string | undefined
   */
  level_id?: string
  /**
   * @type string | undefined
   */
  language?: CodingLanguage
  /**
   * @description The function signature
   * @type string | undefined
   */
  function?: string
}