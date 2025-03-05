import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetERByTagsQueryResponse, GetERByTagsQueryParams } from '../../models/GetERByTags.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getERByTagsQueryKey = (params: GetERByTagsQueryParams) => ['v1', { url: '/session/tags' }, ...(params ? [params] : [])] as const

export type GetERByTagsQueryKey = ReturnType<typeof getERByTagsQueryKey>

/**
 * @description Retrieves a list of escape-room instances filtered by tags
 * @summary Get all escape-room instances having specific tags
 * {@link /session/tags}
 */
export async function getERByTagsHook(params: GetERByTagsQueryParams, config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERByTagsQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/session/tags`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1',
    params,
    ...requestConfig,
  })
  return res.data
}

export function getERByTagsQueryOptionsHook(params: GetERByTagsQueryParams, config: Partial<RequestConfig> = {}) {
  const queryKey = getERByTagsQueryKey(params)
  return queryOptions<GetERByTagsQueryResponse, ResponseErrorConfig<Error>, GetERByTagsQueryResponse, typeof queryKey>({
    enabled: !!params,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERByTagsHook(params, config)
    },
  })
}

/**
 * @description Retrieves a list of escape-room instances filtered by tags
 * @summary Get all escape-room instances having specific tags
 * {@link /session/tags}
 */
export function useGetERByTagsHook<TData = GetERByTagsQueryResponse, TQueryData = GetERByTagsQueryResponse, TQueryKey extends QueryKey = GetERByTagsQueryKey>(
  params: GetERByTagsQueryParams,
  options: {
    query?: Partial<QueryObserverOptions<GetERByTagsQueryResponse, ResponseErrorConfig<Error>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERByTagsQueryKey(params)

  const query = useQuery({
    ...(getERByTagsQueryOptionsHook(params, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}