// version: 1.0
import type { SessionResponse } from './SessionResponse.ts'

/**
 * @description OK
 */
export type GetERHistory200 = SessionResponse[]

export type GetERHistoryQueryResponse = GetERHistory200

export type GetERHistoryQuery = {
  Response: GetERHistory200
  Errors: any
}