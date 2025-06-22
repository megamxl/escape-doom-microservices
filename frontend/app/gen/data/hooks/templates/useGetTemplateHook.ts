import client from '@kubb/plugin-client/clients/axios'
import type { GetTemplateQueryResponse, GetTemplatePathParams, GetTemplate404, GetTemplate500 } from '../../models/GetTemplate.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getTemplateQueryKey = ({ templateId }: { templateId: GetTemplatePathParams['template-id'] }) =>
  ['v1', { url: '/templates/:template-id', params: { templateId: templateId } }] as const

export type GetTemplateQueryKey = ReturnType<typeof getTemplateQueryKey>

/**
 * @description Retrieve details of a specific Template using its unique ID
 * @summary Get a specific Template by ID
 * {@link /templates/:template-id}
 */
export async function getTemplateHook(
  { templateId }: { templateId: GetTemplatePathParams['template-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetTemplateQueryResponse, ResponseErrorConfig<GetTemplate404 | GetTemplate500>, unknown>({
    method: 'GET',
    url: `/templates/${templateId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getTemplateQueryOptionsHook({ templateId }: { templateId: GetTemplatePathParams['template-id'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getTemplateQueryKey({ templateId })
  return queryOptions<GetTemplateQueryResponse, ResponseErrorConfig<GetTemplate404 | GetTemplate500>, GetTemplateQueryResponse, typeof queryKey>({
    enabled: !!templateId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getTemplateHook({ templateId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific Template using its unique ID
 * @summary Get a specific Template by ID
 * {@link /templates/:template-id}
 */
export function useGetTemplateHook<TData = GetTemplateQueryResponse, TQueryData = GetTemplateQueryResponse, TQueryKey extends QueryKey = GetTemplateQueryKey>(
  { templateId }: { templateId: GetTemplatePathParams['template-id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetTemplateQueryResponse, ResponseErrorConfig<GetTemplate404 | GetTemplate500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getTemplateQueryKey({ templateId })

  const query = useQuery({
    ...(getTemplateQueryOptionsHook({ templateId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetTemplate404 | GetTemplate500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}