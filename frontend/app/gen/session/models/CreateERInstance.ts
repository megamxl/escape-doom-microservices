// version: 1.0
import type { EscapeRoomCreation } from './EscapeRoomCreation.ts'
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'

/**
 * @description OK
 */
export type CreateERInstance200 = EscapeRoomSessionResponse

/**
 * @description The escape-room template to use
 */
export type CreateERInstanceMutationRequest = EscapeRoomCreation

export type CreateERInstanceMutationResponse = CreateERInstance200

export type CreateERInstanceMutation = {
  Response: CreateERInstance200
  Request: CreateERInstanceMutationRequest
  Errors: any
}