// version: 0.0.1
import type { NodeSpecificsDTO } from './NodeSpecificsDTO.ts'

/**
 * @description Console node specifics - Used for the frontend type-safety
 */
export type ConsoleNodeSpecificsDTO = NodeSpecificsDTO & {
  /**
   * @description Description about the input properties
   * @type string | undefined
   */
  return_description?: string
  /**
   * @description Any code-details helpful for the user
   * @type string | undefined
   */
  constraints?: string
  /**
   * @description A code-example in somewhat human-readable form
   * @type string | undefined
   */
  example?: string
}