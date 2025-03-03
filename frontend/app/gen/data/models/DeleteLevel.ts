// version: 0.0.1

export type DeleteLevelPathParams = {
  /**
   * @description The unique ID of the EscapeRoomLevel to delete
   * @type string
   */
  'escape-room-level-id': string
}

/**
 * @description Level deleted successfully
 */
export type DeleteLevel200 = {
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
export type DeleteLevel404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type DeleteLevel500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type DeleteLevelMutationResponse = DeleteLevel200

export type DeleteLevelMutation = {
  Response: DeleteLevel200
  PathParams: DeleteLevelPathParams
  Errors: DeleteLevel404 | DeleteLevel500
}