// version: 0.0.1
import type { RiddleCreationRequestDTO } from './RiddleCreationRequestDTO.ts'
import type { RiddleDTO } from './RiddleDTO.ts'

/**
 * @description Riddle created successfully
 */
export type CreateRiddle201 = RiddleDTO

/**
 * @description Bad Request
 */
export type CreateRiddle400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type CreateRiddle500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The details of the riddle to create
 */
export type CreateRiddleMutationRequest = RiddleCreationRequestDTO

export type CreateRiddleMutationResponse = CreateRiddle201

export type CreateRiddleMutation = {
  Response: CreateRiddle201
  Request: CreateRiddleMutationRequest
  Errors: CreateRiddle400 | CreateRiddle500
}