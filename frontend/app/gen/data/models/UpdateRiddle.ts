// version: 0.0.1
import type { RiddleCreationRequestDTO } from './RiddleCreationRequestDTO.ts'
import type { RiddleDTO } from './RiddleDTO.ts'

export type UpdateRiddlePathParams = {
  /**
   * @description The unique ID of the riddle
   * @type string
   */
  'riddle-id': string
}

/**
 * @description Riddle updated successfully
 */
export type UpdateRiddle200 = RiddleDTO

/**
 * @description Bad Request
 */
export type UpdateRiddle400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type UpdateRiddle404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type UpdateRiddle500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The override details of the riddle
 */
export type UpdateRiddleMutationRequest = RiddleCreationRequestDTO

export type UpdateRiddleMutationResponse = UpdateRiddle200

export type UpdateRiddleMutation = {
  Response: UpdateRiddle200
  Request: UpdateRiddleMutationRequest
  PathParams: UpdateRiddlePathParams
  Errors: UpdateRiddle400 | UpdateRiddle404 | UpdateRiddle500
}