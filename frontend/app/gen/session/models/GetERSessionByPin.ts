// version: 1.0
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'

export type GetERSessionByPinPathParams = {
  /**
   * @description The pin to join the escape-room
   * @minLength 100000
   * @maxLength 999999
   * @type integer
   */
  room_pin: number
}

/**
 * @description OK
 */
export type GetERSessionByPin200 = EscapeRoomSessionResponse

export type GetERSessionByPinQueryResponse = GetERSessionByPin200

export type GetERSessionByPinQuery = {
  Response: GetERSessionByPin200
  PathParams: GetERSessionByPinPathParams
  Errors: any
}