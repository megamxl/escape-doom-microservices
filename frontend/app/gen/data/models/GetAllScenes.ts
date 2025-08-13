// version: 0.0.1
import type { SceneDTO } from './SceneDTO.ts'

/**
 * @description A list of scenes
 */
export type GetAllScenes200 = SceneDTO[]

/**
 * @description Internal Server Error
 */
export type GetAllScenes500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetAllScenesQueryResponse = GetAllScenes200

export type GetAllScenesQuery = {
  Response: GetAllScenes200
  Errors: GetAllScenes500
}