// version: 0.0.1
import type { FullExportResponse } from './FullExportResponse.ts'

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
export type GetFullExportRoomPin200 = FullExportResponse[]

export type GetFullExportRoomPinQueryResponse = GetFullExportRoomPin200

export type GetFullExportRoomPinQuery = {
  Response: GetFullExportRoomPin200
  PathParams: GetFullExportRoomPinPathParams
  Errors: any
}