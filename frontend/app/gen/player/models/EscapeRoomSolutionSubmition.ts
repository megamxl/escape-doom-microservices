// version: 1.0

export const escapeRoomSolutionSubmitionLanguageEnum = {
  JAVA: 'JAVA',
  PYTHON: 'PYTHON',
  JS: 'JS',
} as const

export type EscapeRoomSolutionSubmitionLanguageEnum = (typeof escapeRoomSolutionSubmitionLanguageEnum)[keyof typeof escapeRoomSolutionSubmitionLanguageEnum]

/**
 * @description The solution to submit
 */
export type EscapeRoomSolutionSubmition = {
  /**
   * @description The solution to submit
   * @type string | undefined
   */
  solution?: string
  /**
   * @description The language of the solution
   * @type string | undefined
   */
  language?: EscapeRoomSolutionSubmitionLanguageEnum
}