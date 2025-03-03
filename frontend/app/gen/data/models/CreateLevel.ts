// version: 0.0.1
import type { EscapeRoomLevelDTO } from './EscapeRoomLevelDTO.ts'

/**
 * @description Level created successfully
 */
export type CreateLevel201 = EscapeRoomLevelDTO

/**
 * @description Bad Request
 */
export type CreateLevel400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type CreateLevel500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The details of the new EscapeRoomLevel
 */
export type CreateLevelMutationRequest = EscapeRoomLevelDTO

export type CreateLevelMutationResponse = CreateLevel201

export type CreateLevelMutation = {
  Response: CreateLevel201
  Request: CreateLevelMutationRequest
  Errors: CreateLevel400 | CreateLevel500
}