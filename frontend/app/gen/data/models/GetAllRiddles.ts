// version: 0.0.1
import type { Riddle } from './Riddle.ts'

/**
 * @description A list of riddles
 */
export type GetAllRiddles200 = Riddle[]

/**
 * @description Internal Server Error
 */
export type GetAllRiddles500 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

export type GetAllRiddlesQueryResponse = GetAllRiddles200

export type GetAllRiddlesQuery = {
  Response: GetAllRiddles200
  Errors: GetAllRiddles500
}