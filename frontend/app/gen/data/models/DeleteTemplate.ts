// version: 0.0.1
import type { TemplateResultDTO } from './TemplateResultDTO.ts'

export type DeleteTemplatePathParams = {
  /**
   * @description The unique ID of the Template to delete
   * @type string
   */
  'template-id': string
}

/**
 * @description Operation result for Template
 */
export type DeleteTemplate200 = TemplateResultDTO

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