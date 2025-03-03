// version: 0.0.1
import type { EscapeRoomTemplateDTO } from './EscapeRoomTemplateDTO.ts'

export type GetTemplatePathParams = {
  /**
   * @description The unique ID of the EscapeRoomTemplate
   * @type string
   */
  'escape-room-template-id': string
}

/**
 * @description Details of the specified template
 */
export type GetTemplate200 = EscapeRoomTemplateDTO

/**
 * @description Template not found
 */
export type GetTemplate404 = {
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
export type GetTemplate500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetTemplateQueryResponse = GetTemplate200

export type GetTemplateQuery = {
  Response: GetTemplate200
  PathParams: GetTemplatePathParams
  Errors: GetTemplate404 | GetTemplate500
}