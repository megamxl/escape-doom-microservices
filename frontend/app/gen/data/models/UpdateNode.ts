// version: 0.0.1
import type { NodeDTO } from './NodeDTO.ts'

export type UpdateNodePathParams = {
  /**
   * @description The unique ID of the node
   * @type string
   */
  'node-id': string
}

/**
 * @description Node updated successfully
 */
export type UpdateNode200 = NodeDTO

/**
 * @description Bad Request
 */
export type UpdateNode400 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Not Found
 */
export type UpdateNode404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type UpdateNode500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description The overridden details of the node
 */
export type UpdateNodeMutationRequest = NodeDTO

export type UpdateNodeMutationResponse = UpdateNode200

export type UpdateNodeMutation = {
  Response: UpdateNode200
  Request: UpdateNodeMutationRequest
  PathParams: UpdateNodePathParams
  Errors: UpdateNode400 | UpdateNode404 | UpdateNode500
}