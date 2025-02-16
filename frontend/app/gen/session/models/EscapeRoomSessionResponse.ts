// version: 1.0
import type { EscapeRoomState } from './EscapeRoomState.ts'

/**
 * @description The escape-room instance
 */
export type EscapeRoomSessionResponse = {
  /**
   * @type string | undefined
   */
  state?: EscapeRoomState
  /**
   * @description The id of the escape-room template used
   * @type string | undefined, uuid
   */
  escape_room_template_id?: string
  /**
   * @description The id of the escape-room session
   * @type string | undefined, uuid
   */
  escape_room_session_id?: string
  /**
   * @description The time in minutes the escape-room will be played for
   * @minLength 1
   * @maxLength 180
   * @default 60
   * @type integer | undefined
   */
  play_time?: number
  /**
   * @description The pin to join the escape-room
   * @minLength 100000
   * @maxLength 999999
   * @type integer | undefined
   */
  room_pin?: number
  /**
   * @type array | undefined
   */
  tags?: string[]
}