// version: 0.0.1
import type { Node } from './Node.ts'

/**
 * @description A scene of an escape-room instance
 */
export type Scene = {
  /**
   * @description The unique ID of the scene sequence
   * @type string | undefined
   */
  escape_room_sequence_id?: string
  /**
   * @description The nodes of the scene
   * @type array | undefined
   */
  nodes?: Node[]
  /**
   * @description The URI of the background image
   * @type string | undefined
   */
  background_image_uri?: string
  /**
   * @description The name of the scene
   * @type string | undefined
   */
  name?: string
}