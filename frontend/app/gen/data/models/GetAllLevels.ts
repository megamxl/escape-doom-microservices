// version: 0.0.1
import type { EscapeRoomLevelDTO } from './EscapeRoomLevelDTO.ts'

/**
 * @description A list of levels
 */
export type GetAllLevels200 = EscapeRoomLevelDTO[]

/**
 * @description Internal Server Error
 */
export type GetAllLevels500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetAllLevelsQueryResponse = GetAllLevels200

export type GetAllLevelsQuery = {
  Response: GetAllLevels200
  Errors: GetAllLevels500
}