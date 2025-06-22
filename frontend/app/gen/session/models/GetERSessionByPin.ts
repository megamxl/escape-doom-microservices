// version: 1.0
import type { SessionResponse } from './SessionResponse.ts'

export type GetERSessionByPinPathParams = {
  /**
   * @description The 6-digit room pin
   * @minLength 100000
   * @maxLength 999999
   * @type integer
   */
  pin: number
}

/**
 * @description OK
 */
export type GetERSessionByPin200 = SessionResponse

/**
 * @description Not Found
 */
export type GetERSessionByPin404 = any

export type GetERSessionByPinQueryResponse = GetERSessionByPin200

export type GetERSessionByPinQuery = {
  Response: GetERSessionByPin200
  PathParams: GetERSessionByPinPathParams
  Errors: GetERSessionByPin404
}