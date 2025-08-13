// version: 0.0.1
import type { CodingLanguage } from './CodingLanguage.ts'

/**
 * @description Base schema for riddle creation
 */
export type RiddleCreationRequestDTO = {
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
  /**
   * @description The ID of the Escape Room Level this riddle belongs to
   * @type string | undefined
   */
  level_id?: string
}