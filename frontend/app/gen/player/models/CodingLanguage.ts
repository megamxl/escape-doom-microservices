// version: 1.0

export const codingLanguageEnum = {
  JAVA: 'JAVA',
  TYPESCRIPT: 'TYPESCRIPT',
} as const

export type CodingLanguageEnum = (typeof codingLanguageEnum)[keyof typeof codingLanguageEnum]

/**
 * @example JAVA
 */
export type CodingLanguage = CodingLanguageEnum