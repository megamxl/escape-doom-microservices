// version: 0.0.1
import type { EscapeRoomTemplateUpdateRequestDTO } from './EscapeRoomTemplateUpdateRequestDTO.ts'
import type { EscapeRoomTemplateUpdateResultDTO } from './EscapeRoomTemplateUpdateResultDTO.ts'

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
export type PutTemplate200 = EscapeRoomTemplateUpdateResultDTO

/**
 * @description Bad Request
 */
export type PutTemplate400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type PutTemplate404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type PutTemplate500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The updated data for the template
 */
export type PutTemplateMutationRequest = EscapeRoomTemplateUpdateRequestDTO

export type PutTemplateMutationResponse = PutTemplate200

export type PutTemplateMutation = {
  Response: PutTemplate200
  Request: PutTemplateMutationRequest
  PathParams: PutTemplatePathParams
  Errors: PutTemplate400 | PutTemplate404 | PutTemplate500
}