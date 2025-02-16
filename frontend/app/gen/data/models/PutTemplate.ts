// version: 0.0.1
import type { EscapeRoomTemplateUpdateRequest } from './EscapeRoomTemplateUpdateRequest.ts'
import type { EscapeRoomTemplateUpdateResult } from './EscapeRoomTemplateUpdateResult.ts'

export type PutTemplatePathParams = {
  /**
   * @description The unique ID of the EscapeRoomTemplate to update
   * @type string
   */
  'escape-room-template-id': string
}

/**
 * @description Template updated successfully
 */
export type PutTemplate200 = EscapeRoomTemplateUpdateResult

/**
 * @description Bad Request
 */
export type PutTemplate400 = {
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
 * @description Not Found
 */
export type PutTemplate404 = {
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
export type PutTemplate500 = {
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
 * @description The updated data for the template
 */
export type PutTemplateMutationRequest = EscapeRoomTemplateUpdateRequest

export type PutTemplateMutationResponse = PutTemplate200

export type PutTemplateMutation = {
  Response: PutTemplate200
  Request: PutTemplateMutationRequest
  PathParams: PutTemplatePathParams
  Errors: PutTemplate400 | PutTemplate404 | PutTemplate500
}