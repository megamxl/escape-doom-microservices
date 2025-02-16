// version: 0.0.1

/**
 * @description The escape-room template, base for an EscapeRoom Game
 */
export type EscapeRoomTemplateCreateRequest = {
  /**
   * @description Name of the Template
   * @type string | undefined
   */
  name?: string
  /**
   * @description Description of the Template
   * @type string | undefined
   */
  description?: string
}