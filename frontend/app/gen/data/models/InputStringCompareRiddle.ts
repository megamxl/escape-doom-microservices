// version: 0.0.1
import type { Riddle } from './Riddle.ts'

export type InputStringCompareRiddle = Riddle & {
  /**
   * @description The expected output to compare against
   * @type string | undefined
   */
  expected_output?: string
}