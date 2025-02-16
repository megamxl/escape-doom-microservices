// version: 1.0
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'
import type { EscapeRoomState } from './EscapeRoomState.ts'

export type ToggleERInstanceStatePathParams = {
  /**
   * @description The id of the escape-room instance
   * @type string, uuid
   */
  escape_room_session_id: string
  /**
   * @type string
   */
  state: EscapeRoomState
}

/**
 * @description OK
 */
export type ToggleERInstanceState200 = EscapeRoomSessionResponse

export type ToggleERInstanceStateMutationResponse = ToggleERInstanceState200

export type ToggleERInstanceStateMutation = {
  Response: ToggleERInstanceState200
  PathParams: ToggleERInstanceStatePathParams
  Errors: any
}