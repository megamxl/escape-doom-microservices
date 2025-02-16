// version: 1.0

/**
 * @description The escape-room template to use
 */
export type EscapeRoomCreation = {
  /**
   * @description The id of the escape-room template to use
   * @type string | undefined, uuid
   */
  escape_room_template_id?: string
  /**
   * @description The time in minutes the escape-room will be played for
   * @minLength 1
   * @maxLength 180
   * @default 60
   * @type integer | undefined
   */
  play_time?: number
}

export type EscapeRoomCreation = EscapeRoomCreation