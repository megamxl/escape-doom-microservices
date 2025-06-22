// version: 1.0
import type { NodeDTO } from './NodeDTO.ts'

/**
 * @description A scene of an escape-room instance
 */
export type Scene = {
  /**
   * @description The sequence number of the scene
   * @type number | undefined, number
   */
  scene_sequence?: number
  /**
   * @description The nodes of the scene
   * @type array | undefined
   */
  nodes?: NodeDTO[]
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