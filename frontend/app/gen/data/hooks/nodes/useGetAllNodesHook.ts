import client from '@kubb/plugin-client/clients/axios'
import type { GetAllNodesQueryResponse, GetAllNodes500 } from '../../models/GetAllNodes.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getAllNodesQueryKey = () => ['v1', { url: '/nodes' }] as const

export type GetAllNodesQueryKey = ReturnType<typeof getAllNodesQueryKey>

/**
 * @description Retrieve all nodes from a Lector
 * @summary Get all nodes
 * {@link /nodes}
 */
export async function getAllNodesHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllNodesQueryResponse, ResponseErrorConfig<GetAllNodes500>, unknown>({
    method: 'GET',
    url: `/nodes`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllNodesQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllNodesQueryKey()
  return queryOptions<GetAllNodesQueryResponse, ResponseErrorConfig<GetAllNodes500>, GetAllNodesQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllNodesHook(config)
    },
  })
}

/**
 * @description Retrieve all nodes from a Lector
 * @summary Get all nodes
 * {@link /nodes}
 */
export function useGetAllNodesHook<TData = GetAllNodesQueryResponse, TQueryData = GetAllNodesQueryResponse, TQueryKey extends QueryKey = GetAllNodesQueryKey>(
  options: {
    query?: Partial<QueryObserverOptions<GetAllNodesQueryResponse, ResponseErrorConfig<GetAllNodes500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllNodesQueryKey()

  const query = useQuery({
    ...(getAllNodesQueryOptionsHook(config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetAllNodes500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}