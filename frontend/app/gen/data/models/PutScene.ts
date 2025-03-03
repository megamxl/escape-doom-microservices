// version: 0.0.1
import type { SceneDTO } from './SceneDTO.ts'
import type { SceneRequestDTO } from './SceneRequestDTO.ts'

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
export type PutScene200 = SceneDTO

/**
 * @description Bad Request
 */
export type PutScene400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type PutScene404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type PutScene500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The updated details of the Scene
 */
export type PutSceneMutationRequest = SceneRequestDTO

export type PutSceneMutationResponse = PutScene200

export type PutSceneMutation = {
  Response: PutScene200
  Request: PutSceneMutationRequest
  PathParams: PutScenePathParams
  Errors: PutScene400 | PutScene404 | PutScene500
}