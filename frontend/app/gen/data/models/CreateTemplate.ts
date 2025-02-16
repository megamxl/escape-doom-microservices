// version: 0.0.1
import type { EscapeRoomTemplateCreateRequest } from './EscapeRoomTemplateCreateRequest.ts'
import type { EscapeRoomTemplateResult } from './EscapeRoomTemplateResult.ts'

/**
 * @description Operation result for EscapeRoomTemplate
 */
export type CreateTemplate200 = EscapeRoomTemplateResult

/**
 * @description Bad Request
 */
export type CreateTemplate400 = {
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
export type CreateTemplate500 = {
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
 * @description Lectors ID + Name and Description for a Template
 */
export type CreateTemplateMutationRequest = EscapeRoomTemplateCreateRequest

export type CreateTemplateMutationResponse = CreateTemplate200

export type CreateTemplateMutation = {
  Response: CreateTemplate200
  Request: CreateTemplateMutationRequest
  Errors: CreateTemplate400 | CreateTemplate500
}