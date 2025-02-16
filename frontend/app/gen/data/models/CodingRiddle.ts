// version: 0.0.1
import type { Riddle } from './Riddle.ts'

export type CodingRiddle = Riddle & {
  /**
   * @description The programming language used in the riddle
   * @type string | undefined
   */
  language?: string
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
   * @description The expected output of the function
   * @type string | undefined
   */
  expected_output?: string
}