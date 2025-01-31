// version: 0.0.1
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'
import type { UserProgress } from './UserProgress.ts'

export type GetFullExportRoomPinPathParams = {
  /**
   * @description The unique ID of the escape room session.
   * @minLength 100000
   * @maxLength 999999
   * @type integer
   */
  room_pin: number
}

/**
 * @description Full leaderboard
 */
export type GetFullExportRoomPin200 = {
  /**
   * @type object | undefined
   */
  session?: EscapeRoomSessionResponse
  /**
   * @description List of user progress for the session.
   * @type array | undefined
   */
  user_progress?: UserProgress[]
}[]

export type GetFullExportRoomPinQueryResponse = GetFullExportRoomPin200

export type GetFullExportRoomPinQuery = {
  Response: GetFullExportRoomPin200
  PathParams: GetFullExportRoomPinPathParams
  Errors: any
}