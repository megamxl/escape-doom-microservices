// version: 0.0.1
import type { EscapeRoomLevel } from './EscapeRoomLevel.ts'

export type GetLevelPathParams = {
  /**
   * @description The unique ID of the EscapeRoomLevel
   * @type string
   */
  'escape-room-level-id': string
}

/**
 * @description Level details
 */
export type GetLevel200 = EscapeRoomLevel

/**
 * @description Not Found
 */
export type GetLevel404 = {
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
export type GetLevel500 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

export type GetLevelQueryResponse = GetLevel200

export type GetLevelQuery = {
  Response: GetLevel200
  PathParams: GetLevelPathParams
  Errors: GetLevel404 | GetLevel500
}