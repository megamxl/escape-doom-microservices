// version: 0.0.1
import type { NodeSpecificsDTO } from './NodeSpecificsDTO.ts'
import type { PositionDTO } from './PositionDTO.ts'

/**
 * @description The payload for creating a node
 */
export type NodeCreationRequest = {
  /**
   * @description The unique ID of the scene the node belongs to
   * @type string | undefined
   */
  scene_id?: string
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
   * @type object | undefined
   */
  node_specifics?: NodeSpecificsDTO
  /**
   * @type object | undefined
   */
  position?: PositionDTO
}