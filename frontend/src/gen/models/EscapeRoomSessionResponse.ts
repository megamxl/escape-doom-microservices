// version: 0.0.1

export const escapeRoomSessionResponseStateEnum = {
  OPEN: 'OPEN',
  CLOSED: 'CLOSED',
  STARTED: 'STARTED',
  FINISHED: 'FINISHED',
} as const

export type EscapeRoomSessionResponseStateEnum = (typeof escapeRoomSessionResponseStateEnum)[keyof typeof escapeRoomSessionResponseStateEnum]

/**
 * @description Represents metadata for a session in API responses.
 */
export type EscapeRoomSessionResponse = {
  /**
   * @description The state of the session.
   * @type string | undefined
   */
  state?: EscapeRoomSessionResponseStateEnum
  /**
   * @description The time in minutes the escape-room will be played for
   * @minLength 1
   * @maxLength 180
   * @default 60
   * @type integer | undefined
   */
  play_time?: number
  /**
   * @description The PIN to join the session.
   * @type integer | undefined
   */
  room_pin?: number
  /**
   * @description The ID of the escape room template.
   * @type string | undefined, uuid
   */
  escape_room_template_id?: string
  /**
   * @description The unique session ID.
   * @type string | undefined, uuid
   */
  escape_room_session_id?: string
  /**
   * @description Tags associated with the session.
   * @type array | undefined
   */
  tags?: string[]
}