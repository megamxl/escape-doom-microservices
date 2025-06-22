// version: 1.0

export const escapeRoomResultStatusEnum = {
  ERROR: 'ERROR',
  COMPILED: 'COMPILED',
  SUCCESS: 'SUCCESS',
  WAITING: 'WAITING',
  WON: 'WON',
} as const

export type EscapeRoomResultStatusEnum = (typeof escapeRoomResultStatusEnum)[keyof typeof escapeRoomResultStatusEnum]

/**
 * @description The result of a submitted solution
 */
export type EscapeRoomResult = {
  /**
   * @type string | undefined
   */
  status?: EscapeRoomResultStatusEnum
  /**
   * @description The output of the solution
   * @type string | undefined
   */
  output?: string
}