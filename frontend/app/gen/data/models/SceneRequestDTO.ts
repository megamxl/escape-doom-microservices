// version: 0.0.1

/**
 * @description Base schema for any request to the scene API
 */
export type SceneRequestDTO = {
  /**
   * @description Defines the \"position\" of the scene in the escape room
   * @type integer | undefined
   */
  scene_sequence?: number
  /**
   * @description The ID of the Escape Room Level this scene belongs to
   * @type string | undefined
   */
  level_id?: string
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