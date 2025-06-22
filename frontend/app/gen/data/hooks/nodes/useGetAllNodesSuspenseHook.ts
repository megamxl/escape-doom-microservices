import client from '@kubb/plugin-client/clients/axios'
import type { GetAllNodesQueryResponse, GetAllNodes500 } from '../../models/GetAllNodes.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getAllNodesSuspenseQueryKey = () => ['v1', { url: '/nodes' }] as const

export type GetAllNodesSuspenseQueryKey = ReturnType<typeof getAllNodesSuspenseQueryKey>

/**
 * @description Retrieve all nodes from a Lector
 * @summary Get all nodes
 * {@link /nodes}
 */
export async function getAllNodesSuspenseHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllNodesQueryResponse, ResponseErrorConfig<GetAllNodes500>, unknown>({
    method: 'GET',
    url: `/nodes`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllNodesSuspenseQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllNodesSuspenseQueryKey()
  return queryOptions<GetAllNodesQueryResponse, ResponseErrorConfig<GetAllNodes500>, GetAllNodesQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllNodesSuspenseHook(config)
    },
  })
}

/**
 * @description Retrieve all nodes from a Lector
 * @summary Get all nodes
 * {@link /nodes}
 */
export function useGetAllNodesSuspenseHook<
  TData = GetAllNodesQueryResponse,
  TQueryData = GetAllNodesQueryResponse,
  TQueryKey extends QueryKey = GetAllNodesSuspenseQueryKey,
>(
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetAllNodesQueryResponse, ResponseErrorConfig<GetAllNodes500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllNodesSuspenseQueryKey()

  const query = useSuspenseQuery({
    ...(getAllNodesSuspenseQueryOptionsHook(config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetAllNodes500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}