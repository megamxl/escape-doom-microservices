// version: 0.0.1
import type { EscapeRoomTemplateDTO } from './EscapeRoomTemplateDTO.ts'

/**
 * @description A list of templates
 */
export type GetAllTemplates200 = EscapeRoomTemplateDTO[]

/**
 * @description Internal Server Error
 */
export type GetAllTemplates500 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

export type GetAllTemplatesQueryResponse = GetAllTemplates200

export type GetAllTemplatesQuery = {
  Response: GetAllTemplates200
  Errors: GetAllTemplates500
}