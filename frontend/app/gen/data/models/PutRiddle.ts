// version: 0.0.1
import type { RiddleCreationRequestDTO } from './RiddleCreationRequestDTO.ts'
import type { RiddleDTO } from './RiddleDTO.ts'

export type PutRiddlePathParams = {
  /**
   * @description The unique ID of the riddle
   * @type string
   */
  'escape-room-riddle-id': string
}

/**
 * @description Riddle updated successfully
 */
export type PutRiddle200 = RiddleDTO

/**
 * @description Bad Request
 */
export type PutRiddle400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type PutRiddle404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type PutRiddle500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The override details of the riddle
 */
export type PutRiddleMutationRequest = RiddleCreationRequestDTO

export type PutRiddleMutationResponse = PutRiddle200

export type PutRiddleMutation = {
  Response: PutRiddle200
  Request: PutRiddleMutationRequest
  PathParams: PutRiddlePathParams
  Errors: PutRiddle400 | PutRiddle404 | PutRiddle500
}