// version: 0.0.1
import type { NodeDTO } from './NodeDTO.ts'

/**
 * @description A list of nodes
 */
export type GetAllNodes200 = NodeDTO[]

/**
 * @description Internal Server Error
 */
export type GetAllNodes500 = {
  /**
   * @type string | undefined
   */
  message?: string
}

export type GetAllNodesQueryResponse = GetAllNodes200

export type GetAllNodesQuery = {
  Response: GetAllNodes200
  Errors: GetAllNodes500
}