// version: 0.0.1
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'
import type { UserProgress } from './UserProgress.ts'

export type GetEscapeRoomSessionsQueryParams = {
  /**
   * @description A comma-separated list of tags to filter sessions.
   * @type array | undefined
   */
  tags?: string[]
}

/**
 * @description All session leaderboards
 */
export type GetEscapeRoomSessions200 = {
  /**
   * @type object | undefined
   */
  session?: EscapeRoomSessionResponse
  /**
   * @description List of user progress for the session.
   * @type array | undefined
   */
  user_progress?: UserProgress[]
}[]

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