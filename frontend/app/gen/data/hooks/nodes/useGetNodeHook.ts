import client from '@kubb/plugin-client/clients/axios'
import type { GetNodeQueryResponse, GetNodePathParams, GetNode404, GetNode500 } from '../../models/GetNode.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getNodeQueryKey = ({ nodeId }: { nodeId: GetNodePathParams['node-id'] }) => ['v1', { url: '/nodes/:node-id', params: { nodeId: nodeId } }] as const

export type GetNodeQueryKey = ReturnType<typeof getNodeQueryKey>

/**
 * @description Retrieve details of a specific node by its ID
 * @summary Get details of a node
 * {@link /nodes/:node-id}
 */
export async function getNodeHook({ nodeId }: { nodeId: GetNodePathParams['node-id'] }, config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetNodeQueryResponse, ResponseErrorConfig<GetNode404 | GetNode500>, unknown>({
    method: 'GET',
    url: `/nodes/${nodeId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getNodeQueryOptionsHook({ nodeId }: { nodeId: GetNodePathParams['node-id'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getNodeQueryKey({ nodeId })
  return queryOptions<GetNodeQueryResponse, ResponseErrorConfig<GetNode404 | GetNode500>, GetNodeQueryResponse, typeof queryKey>({
    enabled: !!nodeId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getNodeHook({ nodeId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific node by its ID
 * @summary Get details of a node
 * {@link /nodes/:node-id}
 */
export function useGetNodeHook<TData = GetNodeQueryResponse, TQueryData = GetNodeQueryResponse, TQueryKey extends QueryKey = GetNodeQueryKey>(
  { nodeId }: { nodeId: GetNodePathParams['node-id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetNodeQueryResponse, ResponseErrorConfig<GetNode404 | GetNode500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getNodeQueryKey({ nodeId })

  const query = useQuery({
    ...(getNodeQueryOptionsHook({ nodeId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetNode404 | GetNode500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}