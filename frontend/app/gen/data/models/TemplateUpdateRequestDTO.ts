// version: 0.0.1
import type { LevelDTO } from './LevelDTO.ts'

/**
 * @description The payload for updating an Template
 */
export type TemplateUpdateRequestDTO = {
  /**
   * @description The updated name of the template
   * @type string | undefined
   */
  name?: string
  /**
   * @description The updated description of the template
   * @type string | undefined
   */
  description?: string
  /**
   * @description The updated levels for the escape-room template
   * @type array | undefined
   */
  levels?: LevelDTO[]
}