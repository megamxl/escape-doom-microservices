// version: 0.0.1
import type { Scene } from './Scene.ts'

/**
 * @description A list of scenes
 */
export type GetAllScenes200 = Scene[]

/**
 * @description Internal Server Error
 */
export type GetAllScenes500 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

export type GetAllScenesQueryResponse = GetAllScenes200

export type GetAllScenesQuery = {
  Response: GetAllScenes200
  Errors: GetAllScenes500
}