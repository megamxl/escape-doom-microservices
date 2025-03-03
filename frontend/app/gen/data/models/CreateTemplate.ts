// version: 0.0.1
import type { EscapeRoomTemplateCreateRequestDTO } from './EscapeRoomTemplateCreateRequestDTO.ts'
import type { EscapeRoomTemplateResultDTO } from './EscapeRoomTemplateResultDTO.ts'

/**
 * @description Operation result for EscapeRoomTemplate
 */
export type CreateTemplate200 = EscapeRoomTemplateResultDTO

/**
 * @description Bad Request
 */
export type CreateTemplate400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type CreateTemplate500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Lectors ID + Name and Description for a Template
 */
export type CreateTemplateMutationRequest = EscapeRoomTemplateCreateRequestDTO

export type CreateTemplateMutationResponse = CreateTemplate200

export type CreateTemplateMutation = {
  Response: CreateTemplate200
  Request: CreateTemplateMutationRequest
  Errors: CreateTemplate400 | CreateTemplate500
}