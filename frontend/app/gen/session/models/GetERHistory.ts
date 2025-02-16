// version: 1.0
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'

/**
 * @description OK
 */
export type GetERHistory200 = EscapeRoomSessionResponse[]

export type GetERHistoryQueryResponse = GetERHistory200

export type GetERHistoryQuery = {
  Response: GetERHistory200
  Errors: any
}