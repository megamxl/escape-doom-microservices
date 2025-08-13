// version: 0.0.1
import type { NodeSpecificsDTO } from './NodeSpecificsDTO.ts'

/**
 * @description Detail node specifics - Used for the frontend type-safety
 */
export type DetailsNodeSpecificsDTO = NodeSpecificsDTO & {
  /**
   * @description A Web-URL to the Image
   * @type string | undefined
   */
  image_uri?: string
}