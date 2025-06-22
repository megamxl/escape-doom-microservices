import client from '@kubb/plugin-client/clients/axios'
import type {
  GetLevelsByTemplateQueryResponse,
  GetLevelsByTemplatePathParams,
  GetLevelsByTemplate404,
  GetLevelsByTemplate500,
} from '../../models/GetLevelsByTemplate.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getLevelsByTemplateSuspenseQueryKey = ({ templateId }: { templateId: GetLevelsByTemplatePathParams['template-id'] }) =>
  ['v1', { url: '/templates/:template-id/levels', params: { templateId: templateId } }] as const

export type GetLevelsByTemplateSuspenseQueryKey = ReturnType<typeof getLevelsByTemplateSuspenseQueryKey>

/**
 * @description Retrieve all levels associated with a given template
 * @summary Get levels for a specific template
 * {@link /templates/:template-id/levels}
 */
export async function getLevelsByTemplateSuspenseHook(
  { templateId }: { templateId: GetLevelsByTemplatePathParams['template-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelsByTemplateQueryResponse, ResponseErrorConfig<GetLevelsByTemplate404 | GetLevelsByTemplate500>, unknown>({
    method: 'GET',
    url: `/templates/${templateId}/levels`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getLevelsByTemplateSuspenseQueryOptionsHook(
  { templateId }: { templateId: GetLevelsByTemplatePathParams['template-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelsByTemplateSuspenseQueryKey({ templateId })
  return queryOptions<
    GetLevelsByTemplateQueryResponse,
    ResponseErrorConfig<GetLevelsByTemplate404 | GetLevelsByTemplate500>,
    GetLevelsByTemplateQueryResponse,
    typeof queryKey
  >({
    enabled: !!templateId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelsByTemplateSuspenseHook({ templateId }, config)
    },
  })
}

/**
 * @description Retrieve all levels associated with a given template
 * @summary Get levels for a specific template
 * {@link /templates/:template-id/levels}
 */
export function useGetLevelsByTemplateSuspenseHook<
  TData = GetLevelsByTemplateQueryResponse,
  TQueryData = GetLevelsByTemplateQueryResponse,
  TQueryKey extends QueryKey = GetLevelsByTemplateSuspenseQueryKey,
>(
  { templateId }: { templateId: GetLevelsByTemplatePathParams['template-id'] },
  options: {
    query?: Partial<
      UseSuspenseQueryOptions<GetLevelsByTemplateQueryResponse, ResponseErrorConfig<GetLevelsByTemplate404 | GetLevelsByTemplate500>, TData, TQueryKey>
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelsByTemplateSuspenseQueryKey({ templateId })

  const query = useSuspenseQuery({
    ...(getLevelsByTemplateSuspenseQueryOptionsHook({ templateId }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetLevelsByTemplate404 | GetLevelsByTemplate500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}