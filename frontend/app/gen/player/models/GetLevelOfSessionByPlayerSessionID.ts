// version: 1.0
import type { EscapeRoomLevel } from './EscapeRoomLevel.ts'

export type GetLevelOfSessionByPlayerSessionIDPathParams = {
  /**
   * @description The session-id of the player
   * @type string, uuid
   */
  player_session_id: string
}

/**
 * @description OK
 */
export type GetLevelOfSessionByPlayerSessionID200 = EscapeRoomLevel

/**
 * @description Internal Server Error
 */
export type GetLevelOfSessionByPlayerSessionID500 = any

export type GetLevelOfSessionByPlayerSessionIDQueryResponse = GetLevelOfSessionByPlayerSessionID200

export type GetLevelOfSessionByPlayerSessionIDQuery = {
  Response: GetLevelOfSessionByPlayerSessionID200
  PathParams: GetLevelOfSessionByPlayerSessionIDPathParams
  Errors: GetLevelOfSessionByPlayerSessionID500
}