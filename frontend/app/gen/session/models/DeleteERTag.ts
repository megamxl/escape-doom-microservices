// version: 1.0
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'

export type DeleteERTagPathParams = {
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
export type DeleteERTag200 = EscapeRoomSessionResponse

export type DeleteERTagMutationResponse = DeleteERTag200

export type DeleteERTagMutation = {
  Response: DeleteERTag200
  PathParams: DeleteERTagPathParams
  Errors: any
}