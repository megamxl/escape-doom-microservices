// version: 0.0.1
import type { LevelDTO } from './LevelDTO.ts'

export type GetLevelPathParams = {
  /**
   * @description The unique ID of the Level
   * @type string
   */
  'level-id': string
}

/**
 * @description Level details
 */
export type GetLevel200 = LevelDTO

/**
 * @description Not Found
 */
export type GetLevel404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type GetLevel500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetLevelQueryResponse = GetLevel200

export type GetLevelQuery = {
  Response: GetLevel200
  PathParams: GetLevelPathParams
  Errors: GetLevel404 | GetLevel500
}