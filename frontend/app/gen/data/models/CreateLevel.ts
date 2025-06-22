// version: 0.0.1
import type { LevelCreationRequest } from './LevelCreationRequest.ts'
import type { LevelDTO } from './LevelDTO.ts'

/**
 * @description Level created successfully
 */
export type CreateLevel201 = LevelDTO

/**
 * @description Bad Request
 */
export type CreateLevel400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type CreateLevel500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The details of the new Level
 */
export type CreateLevelMutationRequest = LevelCreationRequest

export type CreateLevelMutationResponse = CreateLevel201

export type CreateLevelMutation = {
  Response: CreateLevel201
  Request: CreateLevelMutationRequest
  Errors: CreateLevel400 | CreateLevel500
}