// version: 0.0.1
import type { Riddle } from './Riddle.ts'
import type { RiddleCreationRequest } from './RiddleCreationRequest.ts'

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
export type CreateRiddleMutationRequest = RiddleCreationRequest

export type CreateRiddleMutationResponse = CreateRiddle201

export type CreateRiddleMutation = {
  Response: CreateRiddle201
  Request: CreateRiddleMutationRequest
  Errors: CreateRiddle400 | CreateRiddle500
}