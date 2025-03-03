// version: 0.0.1
import type { SceneDTO } from './SceneDTO.ts'
import type { SceneRequestDTO } from './SceneRequestDTO.ts'

/**
 * @description Scene created successfully
 */
export type CreateScene201 = SceneDTO

/**
 * @description Bad Request
 */
export type CreateScene400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type CreateScene500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The details of the new Scene
 */
export type CreateSceneMutationRequest = SceneRequestDTO

export type CreateSceneMutationResponse = CreateScene201

export type CreateSceneMutation = {
  Response: CreateScene201
  Request: CreateSceneMutationRequest
  Errors: CreateScene400 | CreateScene500
}