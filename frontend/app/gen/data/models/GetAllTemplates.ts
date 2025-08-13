// version: 0.0.1
import type { TemplateDTO } from './TemplateDTO.ts'

/**
 * @description A list of templates
 */
export type GetAllTemplates200 = TemplateDTO[]

/**
 * @description Internal Server Error
 */
export type GetAllTemplates500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetAllTemplatesQueryResponse = GetAllTemplates200

export type GetAllTemplatesQuery = {
  Response: GetAllTemplates200
  Errors: GetAllTemplates500
}