import client from '@kubb/plugin-client/clients/axios'
import type { GetERHistoryQueryResponse } from '../../models/GetERHistory.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getERHistorySuspenseQueryKey = () => ['v1', { url: '/history' }] as const

export type GetERHistorySuspenseQueryKey = ReturnType<typeof getERHistorySuspenseQueryKey>

/**
 * @description Get the history of all escape-room instances of a lector
 * @summary Get the history of all escape-room instances of a lector
 * {@link /history}
 */
export async function getERHistorySuspenseHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERHistoryQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/history`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getERHistorySuspenseQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getERHistorySuspenseQueryKey()
  return queryOptions<GetERHistoryQueryResponse, ResponseErrorConfig<Error>, GetERHistoryQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERHistorySuspenseHook(config)
    },
  })
}

/**
 * @description Get the history of all escape-room instances of a lector
 * @summary Get the history of all escape-room instances of a lector
 * {@link /history}
 */
export function useGetERHistorySuspenseHook<
  TData = GetERHistoryQueryResponse,
  TQueryData = GetERHistoryQueryResponse,
  TQueryKey extends QueryKey = GetERHistorySuspenseQueryKey,
>(
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetERHistoryQueryResponse, ResponseErrorConfig<Error>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERHistorySuspenseQueryKey()

  const query = useSuspenseQuery({
    ...(getERHistorySuspenseQueryOptionsHook(config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}