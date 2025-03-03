// version: 0.0.1
import type { RiddleDTO } from './RiddleDTO.ts'

/**
 * @description A list of riddles
 */
export type GetAllRiddles200 = RiddleDTO[]

/**
 * @description Internal Server Error
 */
export type GetAllRiddles500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetAllRiddlesQueryResponse = GetAllRiddles200

export type GetAllRiddlesQuery = {
  Response: GetAllRiddles200
  Errors: GetAllRiddles500
}