// version: 0.0.1

/**
 * @description The escape-room template, base for an EscapeRoom Game
 */
export type EscapeRoomTemplateDTO = {
  /**
   * @description Unique ID for the Template
   * @type string | undefined
   */
  escape_room_template_id?: string
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