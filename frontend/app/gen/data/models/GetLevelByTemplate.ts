// version: 0.0.1

export type GetLevelByTemplatePathParams = {
  /**
   * @type string
   */
  'escape-room-template-id': string
}

/**
 * @description Success
 */
export type GetLevelByTemplate200 = any

export type GetLevelByTemplateQueryResponse = GetLevelByTemplate200

export type GetLevelByTemplateQuery = {
  Response: GetLevelByTemplate200
  PathParams: GetLevelByTemplatePathParams
  Errors: any
}