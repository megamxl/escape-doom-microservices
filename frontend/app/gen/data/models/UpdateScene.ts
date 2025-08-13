// version: 0.0.1
import type { SceneDTO } from './SceneDTO.ts'
import type { SceneRequestDTO } from './SceneRequestDTO.ts'

export type UpdateScenePathParams = {
  /**
   * @description The unique ID of the Scene
   * @type string
   */
  'scene-id': string
}

/**
 * @description Scene updated successfully
 */
export type UpdateScene200 = SceneDTO

/**
 * @description Bad Request
 */
export type UpdateScene400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type UpdateScene404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type UpdateScene500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The updated details of the Scene
 */
export type UpdateSceneMutationRequest = SceneRequestDTO

export type UpdateSceneMutationResponse = UpdateScene200

export type UpdateSceneMutation = {
  Response: UpdateScene200
  Request: UpdateSceneMutationRequest
  PathParams: UpdateScenePathParams
  Errors: UpdateScene400 | UpdateScene404 | UpdateScene500
}