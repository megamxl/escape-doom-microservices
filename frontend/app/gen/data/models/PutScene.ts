// version: 0.0.1
import type { Scene } from './Scene.ts'

export type PutScenePathParams = {
  /**
   * @description The unique ID of the Scene
   * @type string
   */
  'escape-room-scene-id': string
}

/**
 * @description Scene updated successfully
 */
export type PutScene200 = Scene

/**
 * @description Bad Request
 */
export type PutScene400 = {
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
export type PutScene404 = {
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
export type PutScene500 = {
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
 * @description The updated details of the Scene
 */
export type PutSceneMutationRequest = Scene

export type PutSceneMutationResponse = PutScene200

export type PutSceneMutation = {
  Response: PutScene200
  Request: PutSceneMutationRequest
  PathParams: PutScenePathParams
  Errors: PutScene400 | PutScene404 | PutScene500
}