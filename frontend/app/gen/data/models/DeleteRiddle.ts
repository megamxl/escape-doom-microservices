// version: 0.0.1

export type DeleteRiddlePathParams = {
  /**
   * @description The unique ID of the riddle
   * @type string
   */
  'escape-room-riddle-id': string
}

/**
 * @description Riddle deleted successfully
 */
export type DeleteRiddle200 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type DeleteRiddle404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type DeleteRiddle500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type DeleteRiddleMutationResponse = DeleteRiddle200

export type DeleteRiddleMutation = {
  Response: DeleteRiddle200
  PathParams: DeleteRiddlePathParams
  Errors: DeleteRiddle404 | DeleteRiddle500
}