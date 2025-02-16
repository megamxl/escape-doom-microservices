// version: 0.0.1
import type { EscapeRoomLevel } from './EscapeRoomLevel.ts'

export type PutLevelOfTemplatePathParams = {
  /**
   * @description The unique ID of the EscapeRoomLevel
   * @type string
   */
  'escape-room-level-id': string
}

/**
 * @description Level updated successfully
 */
export type PutLevelOfTemplate200 = EscapeRoomLevel

/**
 * @description Bad Request
 */
export type PutLevelOfTemplate400 = {
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
export type PutLevelOfTemplate404 = {
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
export type PutLevelOfTemplate500 = {
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
 * @description The overriden details of the EscapeRoomLevel
 */
export type PutLevelOfTemplateMutationRequest = EscapeRoomLevel

export type PutLevelOfTemplateMutationResponse = PutLevelOfTemplate200

export type PutLevelOfTemplateMutation = {
  Response: PutLevelOfTemplate200
  Request: PutLevelOfTemplateMutationRequest
  PathParams: PutLevelOfTemplatePathParams
  Errors: PutLevelOfTemplate400 | PutLevelOfTemplate404 | PutLevelOfTemplate500
}