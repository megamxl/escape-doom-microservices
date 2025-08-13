// version: 0.0.1
import type { NodeDTO } from './NodeDTO.ts'

export type GetNodePathParams = {
  /**
   * @description The unique ID of the node
   * @type string
   */
  'node-id': string
}

/**
 * @description Node details
 */
export type GetNode200 = NodeDTO

/**
 * @description Not Found
 */
export type GetNode404 = {
  /**
   * @type string | undefined
   */
  message?: string
}

/**
 * @description Internal Server Error
 */
export type GetNode500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetNodeQueryResponse = GetNode200

export type GetNodeQuery = {
  Response: GetNode200
  PathParams: GetNodePathParams
  Errors: GetNode404 | GetNode500
}