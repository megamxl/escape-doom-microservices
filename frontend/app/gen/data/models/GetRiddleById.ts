// version: 0.0.1
import type { RiddleDTO } from './RiddleDTO.ts'

export type GetRiddleByIdPathParams = {
  /**
   * @description The unique ID of the riddle
   * @type string
   */
  'riddle-id': string
}

/**
 * @description The riddle
 */
export type GetRiddleById200 = RiddleDTO

/**
 * @description Internal Server Error
 */
export type GetRiddleById500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetRiddleByIdQueryResponse = GetRiddleById200

export type GetRiddleByIdQuery = {
  Response: GetRiddleById200
  PathParams: GetRiddleByIdPathParams
  Errors: GetRiddleById500
}