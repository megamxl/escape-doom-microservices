// version: 1.0
import type { NodeDTO } from './NodeDTO.ts'

/**
 * @description A scene of an escape-room instance
 */
export type SceneDTO = {
  /**
   * @description The unique ID of the scene
   * @type string | undefined
   */
  scene_id?: string
  /**
   * @description Defines the \"position\" of the scene in the escape room
   * @type integer | undefined
   */
  scene_sequence?: number
  /**
   * @description The ID of the escape room level that contains this riddle
   * @type string | undefined
   */
  level_id?: string
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