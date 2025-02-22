// version: 1.0
import type { ErrorObject } from './ErrorObject.ts'
import type { EscapeRoomJoin } from './EscapeRoomJoin.ts'
import type { EscapeRoomJoinResponse } from './EscapeRoomJoinResponse.ts'

/**
 * @description OK
 */
export type HandlePlayerJoin200 = EscapeRoomJoinResponse

/**
 * @description bad
 */
export type HandlePlayerJoin500 = ErrorObject

/**
 * @description The escape-room instance to join
 */
export type HandlePlayerJoinMutationRequest = EscapeRoomJoin

export type HandlePlayerJoinMutationResponse = HandlePlayerJoin200

export type HandlePlayerJoinMutation = {
  Response: HandlePlayerJoin200
  Request: HandlePlayerJoinMutationRequest
  Errors: HandlePlayerJoin500
}