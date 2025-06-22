import client from '@kubb/plugin-client/clients/axios'
import type {
  GetLevelsByTemplateQueryResponse,
  GetLevelsByTemplatePathParams,
  GetLevelsByTemplate404,
  GetLevelsByTemplate500,
} from '../../models/GetLevelsByTemplate.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getLevelsByTemplateQueryKey = ({ templateId }: { templateId: GetLevelsByTemplatePathParams['template-id'] }) =>
  ['v1', { url: '/templates/:template-id/levels', params: { templateId: templateId } }] as const

export type GetLevelsByTemplateQueryKey = ReturnType<typeof getLevelsByTemplateQueryKey>

/**
 * @description Retrieve all levels associated with a given template
 * @summary Get levels for a specific template
 * {@link /templates/:template-id/levels}
 */
export async function getLevelsByTemplateHook(
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

export function getLevelsByTemplateQueryOptionsHook(
  { templateId }: { templateId: GetLevelsByTemplatePathParams['template-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelsByTemplateQueryKey({ templateId })
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
      return getLevelsByTemplateHook({ templateId }, config)
    },
  })
}

/**
 * @description Retrieve all levels associated with a given template
 * @summary Get levels for a specific template
 * {@link /templates/:template-id/levels}
 */
export function useGetLevelsByTemplateHook<
  TData = GetLevelsByTemplateQueryResponse,
  TQueryData = GetLevelsByTemplateQueryResponse,
  TQueryKey extends QueryKey = GetLevelsByTemplateQueryKey,
>(
  { templateId }: { templateId: GetLevelsByTemplatePathParams['template-id'] },
  options: {
    query?: Partial<
      QueryObserverOptions<GetLevelsByTemplateQueryResponse, ResponseErrorConfig<GetLevelsByTemplate404 | GetLevelsByTemplate500>, TData, TQueryData, TQueryKey>
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelsByTemplateQueryKey({ templateId })

  const query = useQuery({
    ...(getLevelsByTemplateQueryOptionsHook({ templateId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetLevelsByTemplate404 | GetLevelsByTemplate500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}