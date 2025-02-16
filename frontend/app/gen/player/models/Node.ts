// version: 1.0
import type { NodeInfo } from './NodeInfo.ts'
import type { NodeType } from './NodeType.ts'
import type { Position } from './Position.ts'

/**
 * @description A node of an escape-room instance
 */
export type Node = {
  /**
   * @type string | undefined
   */
  node_type?: NodeType
  /**
   * @type object | undefined
   */
  node_info?: NodeInfo
  /**
   * @type object | undefined
   */
  position?: Position
}