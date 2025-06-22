// version: 1.0
import type { SessionResponse } from './SessionResponse.ts'

export type DeleteERTagPathParams = {
  /**
   * @description The ID of the escape room session
   * @type string
   */
  session_id: string
  /**
   * @description The name of the tag to remove
   * @type string
   */
  tag_name: string
}

/**
 * @description The tag was added to the escape room
 */
export type DeleteERTag200 = SessionResponse

export type DeleteERTagMutationResponse = DeleteERTag200

export type DeleteERTagMutation = {
  Response: DeleteERTag200
  PathParams: DeleteERTagPathParams
  Errors: any
}