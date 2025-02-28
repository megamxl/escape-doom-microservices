// version: 0.0.1

export const riddleTypeEnum = {
  FOO: 'FOO',
  BOO: 'BOO',
} as const

export type RiddleTypeEnum = (typeof riddleTypeEnum)[keyof typeof riddleTypeEnum]

/**
 * @description The type of a riddle
 */
export type RiddleType = RiddleTypeEnum