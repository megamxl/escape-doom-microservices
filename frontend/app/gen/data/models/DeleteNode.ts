// version: 0.0.1

export type DeleteNodePathParams = {
  /**
   * @description The unique ID of the node
   * @type string
   */
  'node-id': string
}

/**
 * @description Node deleted successfully
 */
export type DeleteNode200 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type DeleteNode404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type DeleteNode500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type DeleteNodeMutationResponse = DeleteNode200

export type DeleteNodeMutation = {
  Response: DeleteNode200
  PathParams: DeleteNodePathParams
  Errors: DeleteNode404 | DeleteNode500
}