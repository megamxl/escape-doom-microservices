// version: 0.0.1
import type { SceneDTO } from './SceneDTO.ts'

export type GetScenePathParams = {
  /**
   * @description The unique ID of the Scene
   * @type string
   */
  'escape-room-scene-id': string
}

/**
 * @description Scene details
 */
export type GetScene200 = SceneDTO

/**
 * @description Not Found
 */
export type GetScene404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type GetScene500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetSceneQueryResponse = GetScene200

export type GetSceneQuery = {
  Response: GetScene200
  PathParams: GetScenePathParams
  Errors: GetScene404 | GetScene500
}