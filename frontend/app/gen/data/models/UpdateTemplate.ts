// version: 0.0.1
import type { TemplateUpdateRequestDTO } from './TemplateUpdateRequestDTO.ts'
import type { TemplateUpdateResultDTO } from './TemplateUpdateResultDTO.ts'

export type UpdateTemplatePathParams = {
  /**
   * @description The unique ID of the Template to update
   * @type string
   */
  'template-id': string
}

/**
 * @description Template updated successfully
 */
export type UpdateTemplate200 = TemplateUpdateResultDTO

/**
 * @description Bad Request
 */
export type UpdateTemplate400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type UpdateTemplate404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type UpdateTemplate500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The updated data for the template
 */
export type UpdateTemplateMutationRequest = TemplateUpdateRequestDTO

export type UpdateTemplateMutationResponse = UpdateTemplate200

export type UpdateTemplateMutation = {
  Response: UpdateTemplate200
  Request: UpdateTemplateMutationRequest
  PathParams: UpdateTemplatePathParams
  Errors: UpdateTemplate400 | UpdateTemplate404 | UpdateTemplate500
}