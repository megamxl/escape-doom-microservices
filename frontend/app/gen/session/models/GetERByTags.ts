// version: 1.0
import type { EscapeRoomSessionResponse } from './EscapeRoomSessionResponse.ts'

export type GetERByTagsQueryParams = {
  /**
   * @description List of tags to filter by
   * @type array
   */
  tags: string[]
}

/**
 * @description OK
 */
export type GetERByTags200 = EscapeRoomSessionResponse[]

export type GetERByTagsQueryResponse = GetERByTags200

export type GetERByTagsQuery = {
  Response: GetERByTags200
  QueryParams: GetERByTagsQueryParams
  Errors: any
}