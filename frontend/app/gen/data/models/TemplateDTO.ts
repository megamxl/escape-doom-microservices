// version: 0.0.1
import type { LevelDTO } from './LevelDTO.ts'

/**
 * @description The escape-room template, base for an  Game
 */
export type TemplateDTO = {
  /**
   * @description Unique ID for the Template
   * @type string | undefined
   */
  template_id?: string
  /**
   * @description Unique ID of the user the escape room belongs to
   * @type string | undefined
   */
  user_id?: string
  /**
   * @description Name of the Template
   * @type string | undefined
   */
  name?: string
  /**
   * @description Description of the Template
   * @type string | undefined
   */
  description?: string
  /**
   * @type array | undefined
   */
  levels?: LevelDTO[]
}