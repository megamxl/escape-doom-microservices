// version: 0.0.1
import type { EscapeRoomLevel } from './EscapeRoomLevel.ts'

/**
 * @description Level created successfully
 */
export type CreateLevel201 = EscapeRoomLevel

/**
 * @description Bad Request
 */
export type CreateLevel400 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

/**
 * @description Internal Server Error
 */
export type CreateLevel500 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

/**
 * @description The details of the new EscapeRoomLevel
 */
export type CreateLevelMutationRequest = EscapeRoomLevel

export type CreateLevelMutationResponse = CreateLevel201

export type CreateLevelMutation = {
  Response: CreateLevel201
  Request: CreateLevelMutationRequest
  Errors: CreateLevel400 | CreateLevel500
}