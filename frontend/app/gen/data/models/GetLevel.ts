// version: 0.0.1
import type { EscapeRoomLevelDTO } from './EscapeRoomLevelDTO.ts'

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
export type GetLevel200 = EscapeRoomLevelDTO

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