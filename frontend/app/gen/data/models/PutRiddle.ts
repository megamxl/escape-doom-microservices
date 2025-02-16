// version: 0.0.1
import type { CodingRiddle } from './CodingRiddle.ts'
import type { InputStringCompareRiddle } from './InputStringCompareRiddle.ts'
import type { Riddle } from './Riddle.ts'

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
export type PutRiddle200 = Riddle

/**
 * @description Bad Request
 */
export type PutRiddle400 = {
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
 * @description Not Found
 */
export type PutRiddle404 = {
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
export type PutRiddle500 = {
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
 * @description The override details of the riddle
 */
export type PutRiddleMutationRequest = InputStringCompareRiddle | CodingRiddle

export type PutRiddleMutationResponse = PutRiddle200

export type PutRiddleMutation = {
  Response: PutRiddle200
  Request: PutRiddleMutationRequest
  PathParams: PutRiddlePathParams
  Errors: PutRiddle400 | PutRiddle404 | PutRiddle500
}