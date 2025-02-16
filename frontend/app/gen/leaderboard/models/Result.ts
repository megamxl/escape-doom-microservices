// version: 0.0.1

/**
 * @description Represents the result of a user\'s progress in a session.
 */
export type Result = {
  /**
   * @description Level of the escape room.
   * @type integer | undefined
   */
  current_escape_room_level?: number
  /**
   * @description User\'s solution attempt.
   * @type string | undefined
   */
  input?: string
  /**
   * @description Date the riddle was solved.
   * @type string | undefined, date-time
   */
  solved_date?: string
  /**
   * @description Points awarded for solving the riddle.
   * @type integer | undefined
   */
  added_points?: number
}