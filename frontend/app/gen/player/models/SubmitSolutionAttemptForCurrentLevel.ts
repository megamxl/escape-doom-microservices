// version: 1.0
import type { EscapeRoomSolutionSubmition } from './EscapeRoomSolutionSubmition.ts'

export type SubmitSolutionAttemptForCurrentLevelPathParams = {
  /**
   * @description The session-id of the player
   * @type string, uuid
   */
  player_session_id: string
}

/**
 * @description OK
 */
export type SubmitSolutionAttemptForCurrentLevel200 = any

/**
 * @description The solution to submit
 */
export type SubmitSolutionAttemptForCurrentLevelMutationRequest = EscapeRoomSolutionSubmition

export type SubmitSolutionAttemptForCurrentLevelMutationResponse = SubmitSolutionAttemptForCurrentLevel200

export type SubmitSolutionAttemptForCurrentLevelMutation = {
  Response: SubmitSolutionAttemptForCurrentLevel200
  Request: SubmitSolutionAttemptForCurrentLevelMutationRequest
  PathParams: SubmitSolutionAttemptForCurrentLevelPathParams
  Errors: any
}