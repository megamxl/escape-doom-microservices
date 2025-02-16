// version: 0.0.1
import type { EscapeRoomLevel } from './EscapeRoomLevel.ts'

/**
 * @description The payload for updating an EscapeRoomTemplate
 */
export type EscapeRoomTemplateUpdateRequest = {
  /**
   * @description The updated name of the template
   * @type string | undefined
   */
  name?: string
  /**
   * @description The updated description of the template
   * @type string | undefined
   */
  description?: string
  /**
   * @description The updated levels for the escape-room template
   * @type array | undefined
   */
  levels?: EscapeRoomLevel[]
}