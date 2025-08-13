// version: 0.0.1
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'
import type { UserProgress } from './UserProgress.ts'

export type FullExportResponse = {
  /**
   * @type object | undefined
   */
  session?: EscapeRoomSessionResponse
  /**
   * @description List of user progress for the session.
   * @type array | undefined
   */
  user_progress?: UserProgress[]
}