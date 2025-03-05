import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetERHistoryQueryResponse } from '../../models/GetERHistory.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getERHistoryQueryKey = () => ['v1', { url: '/history' }] as const

export type GetERHistoryQueryKey = ReturnType<typeof getERHistoryQueryKey>

/**
 * @description Get the history of all escape-room instances of a lector
 * @summary Get the history of all escape-room instances of a lector
 * {@link /history}
 */
export async function getERHistoryHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERHistoryQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/history`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1',
    ...requestConfig,
  })
  return res.data
}

export function getERHistoryQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getERHistoryQueryKey()
  return queryOptions<GetERHistoryQueryResponse, ResponseErrorConfig<Error>, GetERHistoryQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERHistoryHook(config)
    },
  })
}

/**
 * @description Get the history of all escape-room instances of a lector
 * @summary Get the history of all escape-room instances of a lector
 * {@link /history}
 */
export function useGetERHistoryHook<
  TData = GetERHistoryQueryResponse,
  TQueryData = GetERHistoryQueryResponse,
  TQueryKey extends QueryKey = GetERHistoryQueryKey,
>(
  options: {
    query?: Partial<QueryObserverOptions<GetERHistoryQueryResponse, ResponseErrorConfig<Error>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERHistoryQueryKey()

  const query = useQuery({
    ...(getERHistoryQueryOptionsHook(config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}