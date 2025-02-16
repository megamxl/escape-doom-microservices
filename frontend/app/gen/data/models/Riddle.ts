// version: 0.0.1

export const riddleTypeEnum = {
  InputStringCompareRiddle: 'InputStringCompareRiddle',
  CodingRiddle: 'CodingRiddle',
} as const

export type RiddleTypeEnum = (typeof riddleTypeEnum)[keyof typeof riddleTypeEnum]

/**
 * @description Base schema for a riddle
 */
export type Riddle = {
  /**
   * @description The unique ID of the riddle
   * @type string | undefined
   */
  escape_room_riddle_id?: string
  /**
   * @description The type of the riddle
   * @type string | undefined
   */
  type?: RiddleTypeEnum
  /**
   * @description The expected output of the riddle
   * @type string | undefined
   */
  expected_output?: string
}