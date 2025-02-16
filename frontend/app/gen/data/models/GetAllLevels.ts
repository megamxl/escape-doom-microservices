// version: 0.0.1
import type { EscapeRoomLevel } from './EscapeRoomLevel.ts'

/**
 * @description A list of levels
 */
export type GetAllLevels200 = EscapeRoomLevel[]

/**
 * @description Internal Server Error
 */
export type GetAllLevels500 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

export type GetAllLevelsQueryResponse = GetAllLevels200

export type GetAllLevelsQuery = {
  Response: GetAllLevels200
  Errors: GetAllLevels500
}