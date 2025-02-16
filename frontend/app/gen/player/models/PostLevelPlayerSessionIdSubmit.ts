// version: 1.0
import type { EscapeRoomSolutionSubmition } from './EscapeRoomSolutionSubmition.ts'

export type PostLevelPlayerSessionIdSubmitPathParams = {
  /**
   * @description The session-id of the player
   * @type string, uuid
   */
  player_session_id: string
}

/**
 * @description OK
 */
export type PostLevelPlayerSessionIdSubmit200 = any

/**
 * @description The solution to submit
 */
export type PostLevelPlayerSessionIdSubmitMutationRequest = EscapeRoomSolutionSubmition

export type PostLevelPlayerSessionIdSubmitMutationResponse = PostLevelPlayerSessionIdSubmit200

export type PostLevelPlayerSessionIdSubmitMutation = {
  Response: PostLevelPlayerSessionIdSubmit200
  Request: PostLevelPlayerSessionIdSubmitMutationRequest
  PathParams: PostLevelPlayerSessionIdSubmitPathParams
  Errors: any
}