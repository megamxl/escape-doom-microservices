// version: 0.0.1
import type { FullExportResponse } from './FullExportResponse.ts'

export type GetEscapeRoomSessionsQueryParams = {
  /**
   * @description A tags to filter sessions.
   * @type string | undefined
   */
  tag?: string
}

/**
 * @description All session leaderboards
 */
export type GetEscapeRoomSessions200 = FullExportResponse[]

/**
 * @description No sessions found
 */
export type GetEscapeRoomSessions404 = any

export type GetEscapeRoomSessionsQueryResponse = GetEscapeRoomSessions200

export type GetEscapeRoomSessionsQuery = {
  Response: GetEscapeRoomSessions200
  QueryParams: GetEscapeRoomSessionsQueryParams
  Errors: GetEscapeRoomSessions404
}