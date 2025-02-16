// version: 1.0
import type { EscapeRoomLevel } from './EscapeRoomLevel.ts'

export type GetLevelPlayerSessionIdPathParams = {
  /**
   * @description The session-id of the player
   * @type string, uuid
   */
  player_session_id: string
}

/**
 * @description OK
 */
export type GetLevelPlayerSessionId200 = EscapeRoomLevel

/**
 * @description Internal Server Error
 */
export type GetLevelPlayerSessionId500 = any

export type GetLevelPlayerSessionIdQueryResponse = GetLevelPlayerSessionId200

export type GetLevelPlayerSessionIdQuery = {
  Response: GetLevelPlayerSessionId200
  PathParams: GetLevelPlayerSessionIdPathParams
  Errors: GetLevelPlayerSessionId500
}