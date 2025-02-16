// version: 1.0
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'

export type AddERTagPathParams = {
  /**
   * @description The ID of the escape room session
   * @type string
   */
  escape_room_session_id: string
  /**
   * @description The name of the tag to remove
   * @type string
   */
  tag_name: string
}

/**
 * @description The tag was added to the escape room
 */
export type AddERTag200 = EscapeRoomSessionResponse

export type AddERTagMutationResponse = AddERTag200

export type AddERTagMutation = {
  Response: AddERTag200
  PathParams: AddERTagPathParams
  Errors: any
}