// version: 0.0.1
import type { CodingLanguage } from './CodingLanguage.ts'

/**
 * @description Base schema for a riddle
 */
export type Riddle = {
  /**
   * @description The unique ID of the riddle
   * @type string | undefined
   */
  escape_room_riddle_id?: string
  /**
   * @type string | undefined
   */
  language?: CodingLanguage
  /**
   * @description The function signature
   * @type string | undefined
   */
  function_signature?: string
  /**
   * @description The input values for the function
   * @type string | undefined
   */
  input?: string
  /**
   * @description The name of the variable to compare
   * @type string | undefined
   */
  variable_name?: string
  /**
   * @description The expected output of the riddle
   * @type string | undefined
   */
  expected_output?: string
}