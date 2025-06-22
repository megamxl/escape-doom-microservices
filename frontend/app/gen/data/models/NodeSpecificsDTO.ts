// version: 0.0.1
import type { NodeType } from './NodeType.ts'

/**
 * @description Depending on the node type, different params should be given
 */
export type NodeSpecificsDTO = {
  /**
   * @type string | undefined
   */
  node_type?: NodeType
  [key: string]: any
}