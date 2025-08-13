// version: 1.0
import type { EscapeRoomResult } from './EscapeRoomResult.ts'

export type GetLevelResultPathParams = {
  /**
   * @description The session-id of the player
   * @type string, uuid
   */
  player_session_id: string
}

/**
 * @description OK
 */
export type GetLevelResult200 = EscapeRoomResult

export type GetLevelResultQueryResponse = GetLevelResult200

export type GetLevelResultQuery = {
  Response: GetLevelResult200
  PathParams: GetLevelResultPathParams
  Errors: any
}