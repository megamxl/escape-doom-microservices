// version: 0.0.1
import type { LevelDTO } from './LevelDTO.ts'

/**
 * @description A list of levels
 */
export type GetAllLevels200 = LevelDTO[]

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