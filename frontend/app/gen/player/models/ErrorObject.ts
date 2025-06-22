// version: 1.0

/**
 * @description An error returned by the API
 */
export type ErrorObject = {
  /**
   * @description The timestamp of the error
   * @type string | undefined, date-time
   */
  timestamp?: string
  /**
   * @description The path of the error
   * @type string | undefined
   */
  path?: string
  /**
   * @description The status of the error
   * @type string | undefined
   */
  status?: string
  /**
   * @description The error message
   * @type string | undefined
   */
  error?: string
}