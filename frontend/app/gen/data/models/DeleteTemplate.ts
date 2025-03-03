// version: 0.0.1
import type { EscapeRoomTemplateResultDTO } from './EscapeRoomTemplateResultDTO.ts'

export type DeleteTemplatePathParams = {
  /**
   * @description The unique ID of the EscapeRoomTemplate to delete
   * @type string
   */
  'escape-room-template-id': string
}

/**
 * @description Operation result for EscapeRoomTemplate
 */
export type DeleteTemplate200 = EscapeRoomTemplateResultDTO

/**
 * @description Bad Request
 */
export type DeleteTemplate400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type DeleteTemplate404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type DeleteTemplate500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type DeleteTemplateMutationResponse = DeleteTemplate200

export type DeleteTemplateMutation = {
  Response: DeleteTemplate200
  PathParams: DeleteTemplatePathParams
  Errors: DeleteTemplate400 | DeleteTemplate404 | DeleteTemplate500
}