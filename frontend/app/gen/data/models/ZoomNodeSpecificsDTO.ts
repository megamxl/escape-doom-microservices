// version: 0.0.1
import type { NodeSpecificsDTO } from './NodeSpecificsDTO.ts'

/**
 * @description Detail node specifics - Used for the frontend type-safety
 */
export type ZoomNodeSpecificsDTO = NodeSpecificsDTO & {
  /**
   * @description The scene_id of the scene the node links to
   * @type string | undefined, uuid
   */
  linked_scene_id?: string
  /**
   * @description The scene_id of the parent it should return to
   * @type string | undefined, uuid
   */
  parent_scene_id?: string
}