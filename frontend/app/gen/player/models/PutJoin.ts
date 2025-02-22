// version: 1.0
import type { Error } from './Error.ts'
import type { EscapeRoomJoin } from './EscapeRoomJoin.ts'
import type { EscapeRoomJoinResponse } from './EscapeRoomJoinResponse.ts'

/**
 * @description OK
 */
export type PutJoin200 = EscapeRoomJoinResponse

/**
 * @description Internal Server Error
 */
export type PutJoin500 = Error

/**
 * @description The escape-room instance to join
 */
export type PutJoinMutationRequest = EscapeRoomJoin

export type PutJoinMutationResponse = PutJoin200

export type PutJoinMutation = {
  Response: PutJoin200
  Request: PutJoinMutationRequest
  Errors: PutJoin500
}