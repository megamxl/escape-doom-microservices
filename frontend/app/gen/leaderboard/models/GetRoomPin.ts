// version: 0.0.1
import type { UserProgress } from './UserProgress.ts'

export type GetRoomPinPathParams = {
  /**
   * @description The unique ID of the escape room session.
   * @minLength 100000
   * @maxLength 999999
   * @type integer
   */
  room_pin: number
}

/**
 * @description Current session leaderboard
 */
export type GetRoomPin200 = UserProgress[]

/**
 * @description Session not found
 */
export type GetRoomPin404 = any

export type GetRoomPinQueryResponse = GetRoomPin200

export type GetRoomPinQuery = {
  Response: GetRoomPin200
  PathParams: GetRoomPinPathParams
  Errors: GetRoomPin404
}