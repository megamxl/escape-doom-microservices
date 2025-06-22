// version: 0.0.1
import type { LevelDTO } from './LevelDTO.ts'

export type UpdateLevelPathParams = {
  /**
   * @description The unique ID of the Level
   * @type string
   */
  'level-id': string
}

/**
 * @description Level updated successfully
 */
export type UpdateLevel200 = LevelDTO

/**
 * @description Bad Request
 */
export type UpdateLevel400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type UpdateLevel404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type UpdateLevel500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The overridden details of the Level
 */
export type UpdateLevelMutationRequest = LevelDTO

export type UpdateLevelMutationResponse = UpdateLevel200

export type UpdateLevelMutation = {
  Response: UpdateLevel200
  Request: UpdateLevelMutationRequest
  PathParams: UpdateLevelPathParams
  Errors: UpdateLevel400 | UpdateLevel404 | UpdateLevel500
}