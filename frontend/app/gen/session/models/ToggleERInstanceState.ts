// version: 1.0
import type { EscapeRoomState } from './EscapeRoomState.ts'
import type { SessionResponse } from './SessionResponse.ts'

export type ToggleERInstanceStatePathParams = {
  /**
   * @description The id of the escape-room instance
   * @type string, uuid
   */
  session_id: string
  /**
   * @type string
   */
  state: EscapeRoomState
}

/**
 * @description OK
 */
export type ToggleERInstanceState200 = SessionResponse

export type ToggleERInstanceStateMutationResponse = ToggleERInstanceState200

export type ToggleERInstanceStateMutation = {
  Response: ToggleERInstanceState200
  PathParams: ToggleERInstanceStatePathParams
  Errors: any
}