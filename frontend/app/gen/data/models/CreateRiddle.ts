// version: 0.0.1
import type { CodingRiddle } from './CodingRiddle.ts'
import type { InputStringCompareRiddle } from './InputStringCompareRiddle.ts'
import type { Riddle } from './Riddle.ts'

/**
 * @description Riddle created successfully
 */
export type CreateRiddle201 = Riddle

/**
 * @description Bad Request
 */
export type CreateRiddle400 = {
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
export type CreateRiddle500 = {
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
 * @description The details of the riddle to create
 */
export type CreateRiddleMutationRequest = InputStringCompareRiddle | CodingRiddle

export type CreateRiddleMutationResponse = CreateRiddle201

export type CreateRiddleMutation = {
  Response: CreateRiddle201
  Request: CreateRiddleMutationRequest
  Errors: CreateRiddle400 | CreateRiddle500
}