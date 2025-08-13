// version: 0.0.1
import type { LevelDTO } from './LevelDTO.ts'

export type GetLevelsByTemplatePathParams = {
  /**
   * @type string
   */
  'template-id': string
}

/**
 * @description A list of levels
 */
export type GetLevelsByTemplate200 = LevelDTO[]

/**
 * @description Not Found
 */
export type GetLevelsByTemplate404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type GetLevelsByTemplate500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetLevelsByTemplateQueryResponse = GetLevelsByTemplate200

export type GetLevelsByTemplateQuery = {
  Response: GetLevelsByTemplate200
  PathParams: GetLevelsByTemplatePathParams
  Errors: GetLevelsByTemplate404 | GetLevelsByTemplate500
}