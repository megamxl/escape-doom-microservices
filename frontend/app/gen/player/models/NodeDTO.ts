// version: 1.0
import type { NodeSpecificsDTO } from './NodeSpecificsDTO.ts'
import type { PositionDTO } from './PositionDTO.ts'

/**
 * @description A node of an escape-room instance
 */
export type NodeDTO = {
  /**
   * @description The unique identifier of the node
   * @type string | undefined, uuid
   */
  node_id?: string
  /**
   * @description The description of the node
   * @type string | undefined
   */
  description?: string
  /**
   * @description The display title of the node
   * @type string | undefined
   */
  title?: string
  /**
   * @description The unique identifier of the scene the node belongs to
   * @type string | undefined, uuid
   */
  scene_id?: string
  /**
   * @type object | undefined
   */
  node_specifics?: NodeSpecificsDTO
  /**
   * @type object | undefined
   */
  position?: PositionDTO
}