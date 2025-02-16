// version: 0.0.1
import type { Scene } from './Scene.ts'

/**
 * @description Scene created successfully
 */
export type CreateScene201 = Scene

/**
 * @description Bad Request
 */
export type CreateScene400 = {
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
export type CreateScene500 = {
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
 * @description The details of the new Scene
 */
export type CreateSceneMutationRequest = Scene

export type CreateSceneMutationResponse = CreateScene201

export type CreateSceneMutation = {
  Response: CreateScene201
  Request: CreateSceneMutationRequest
  Errors: CreateScene400 | CreateScene500
}