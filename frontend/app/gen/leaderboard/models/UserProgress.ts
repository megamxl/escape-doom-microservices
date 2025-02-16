// version: 0.0.1
import type { Result } from './Result.ts'

/**
 * @description Represents the progress of user in a session.
 */
export type UserProgress = {
  /**
   * @description The session the user participated in.
   * @type string | undefined, uuid
   */
  escape_room_session_id?: string
  /**
   * @description The unique ID of the user.
   * @type string | undefined
   */
  player_name?: string
  /**
   * @description The highest level reached by the user.
   * @type integer | undefined
   */
  current_escape_room_level?: number
  /**
   * @description The timestamp of the last riddle solved.
   * @type string | undefined, date-time
   */
  last_riddle_solved_at?: string
  /**
   * @description The score of the user.
   * @minLength 0
   * @type integer | undefined
   */
  score?: number
  /**
   * @description List of riddle results for the user.
   * @type array | undefined
   */
  results?: Result[]
}