import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetERByTagsQueryResponse, GetERByTagsQueryParams } from '../../models/GetERByTags.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getERByTagsSuspenseQueryKey = (params: GetERByTagsQueryParams) => ['v1', { url: '/session/tags' }, ...(params ? [params] : [])] as const

export type GetERByTagsSuspenseQueryKey = ReturnType<typeof getERByTagsSuspenseQueryKey>

/**
 * @description Retrieves a list of escape-room instances filtered by tags
 * @summary Get all escape-room instances having specific tags
 * {@link /session/tags}
 */
export async function getERByTagsSuspenseHook(params: GetERByTagsQueryParams, config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERByTagsQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/session/tags`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1`,
    params,
    ...requestConfig,
  })
  return res.data
}

export function getERByTagsSuspenseQueryOptionsHook(params: GetERByTagsQueryParams, config: Partial<RequestConfig> = {}) {
  const queryKey = getERByTagsSuspenseQueryKey(params)
  return queryOptions<GetERByTagsQueryResponse, ResponseErrorConfig<Error>, GetERByTagsQueryResponse, typeof queryKey>({
    enabled: !!params,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERByTagsSuspenseHook(params, config)
    },
  })
}

/**
 * @description Retrieves a list of escape-room instances filtered by tags
 * @summary Get all escape-room instances having specific tags
 * {@link /session/tags}
 */
export function useGetERByTagsSuspenseHook<
  TData = GetERByTagsQueryResponse,
  TQueryData = GetERByTagsQueryResponse,
  TQueryKey extends QueryKey = GetERByTagsSuspenseQueryKey,
>(
  params: GetERByTagsQueryParams,
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetERByTagsQueryResponse, ResponseErrorConfig<Error>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERByTagsSuspenseQueryKey(params)

  const query = useSuspenseQuery({
    ...(getERByTagsSuspenseQueryOptionsHook(params, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}