// version: 0.0.1

/**
 * @description The current level of an escape-room instance
 */
export type LevelCreationRequest = {
  /**
   * @description The name of the level
   * @type string | undefined
   */
  name?: string
  /**
   * @description The unique ID of the escape room template
   * @type string | undefined
   */
  template_id?: string
  /**
   * @description The sequence number of the level
   * @type integer | undefined
   */
  level_sequence?: number
}