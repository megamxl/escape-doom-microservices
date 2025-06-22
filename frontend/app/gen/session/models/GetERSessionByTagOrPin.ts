// version: 1.0
import type { SessionResponse } from './SessionResponse.ts'

export type GetERSessionByTagOrPinQueryParams = {
  /**
   * @description The tag to filter sessions by
   * @type string | undefined
   */
  tag?: string
  /**
   * @description The 6-digit room pin
   * @minLength 100000
   * @maxLength 999999
   * @type integer | undefined
   */
  pin?: number
}

/**
 * @description OK
 */
export type GetERSessionByTagOrPin200 = SessionResponse[]

export type GetERSessionByTagOrPinQueryResponse = GetERSessionByTagOrPin200

export type GetERSessionByTagOrPinQuery = {
  Response: GetERSessionByTagOrPin200
  QueryParams: GetERSessionByTagOrPinQueryParams
  Errors: any
}