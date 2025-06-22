// version: 0.0.1
import type { SceneDTO } from './SceneDTO.ts'

export type GetSceneByIdPathParams = {
  /**
   * @description The unique ID of the Scene
   * @type string
   */
  'scene-id': string
}

/**
 * @description Scene details
 */
export type GetSceneById200 = SceneDTO

/**
 * @description Not Found
 */
export type GetSceneById404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type GetSceneById500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetSceneByIdQueryResponse = GetSceneById200

export type GetSceneByIdQuery = {
  Response: GetSceneById200
  PathParams: GetSceneByIdPathParams
  Errors: GetSceneById404 | GetSceneById500
}