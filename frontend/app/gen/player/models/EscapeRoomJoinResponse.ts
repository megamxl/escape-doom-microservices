// version: 1.0
import type { EscapeRoomState } from './EscapeRoomState.ts'

/**
 * @description The escape-room instance
 */
export type EscapeRoomJoinResponse = {
  /**
   * @description The id of the escape-room session for the player
   * @type string | undefined, uuid
   */
  player_session_id?: string
  /**
   * @type string | undefined
   */
  escape_room_state?: EscapeRoomState
  /**
   * @description The name of the player
   * @type string | undefined
   */
  player_name?: string
}