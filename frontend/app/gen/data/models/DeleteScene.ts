// version: 0.0.1

export type DeleteScenePathParams = {
  /**
   * @description The unique ID of the Scene
   * @type string
   */
  'escape-room-scene-id': string
}

/**
 * @description Scene deleted successfully
 */
export type DeleteScene200 = {
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
export type DeleteScene404 = {
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
export type DeleteScene500 = {
  /**
   * @type string | undefined
   */
  message?: string
  /**
   * @type number | undefined
   */
  code?: number
}

export type DeleteSceneMutationResponse = DeleteScene200

export type DeleteSceneMutation = {
  Response: DeleteScene200
  PathParams: DeleteScenePathParams
  Errors: DeleteScene404 | DeleteScene500
}