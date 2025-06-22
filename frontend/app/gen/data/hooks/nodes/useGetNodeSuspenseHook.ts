import client from '@kubb/plugin-client/clients/axios'
import type { GetNodeQueryResponse, GetNodePathParams, GetNode404, GetNode500 } from '../../models/GetNode.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getNodeSuspenseQueryKey = ({ nodeId }: { nodeId: GetNodePathParams['node-id'] }) =>
  ['v1', { url: '/nodes/:node-id', params: { nodeId: nodeId } }] as const

export type GetNodeSuspenseQueryKey = ReturnType<typeof getNodeSuspenseQueryKey>

/**
 * @description Retrieve details of a specific node by its ID
 * @summary Get details of a node
 * {@link /nodes/:node-id}
 */
export async function getNodeSuspenseHook(
  { nodeId }: { nodeId: GetNodePathParams['node-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetNodeQueryResponse, ResponseErrorConfig<GetNode404 | GetNode500>, unknown>({
    method: 'GET',
    url: `/nodes/${nodeId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getNodeSuspenseQueryOptionsHook({ nodeId }: { nodeId: GetNodePathParams['node-id'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getNodeSuspenseQueryKey({ nodeId })
  return queryOptions<GetNodeQueryResponse, ResponseErrorConfig<GetNode404 | GetNode500>, GetNodeQueryResponse, typeof queryKey>({
    enabled: !!nodeId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getNodeSuspenseHook({ nodeId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific node by its ID
 * @summary Get details of a node
 * {@link /nodes/:node-id}
 */
export function useGetNodeSuspenseHook<TData = GetNodeQueryResponse, TQueryData = GetNodeQueryResponse, TQueryKey extends QueryKey = GetNodeSuspenseQueryKey>(
  { nodeId }: { nodeId: GetNodePathParams['node-id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetNodeQueryResponse, ResponseErrorConfig<GetNode404 | GetNode500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getNodeSuspenseQueryKey({ nodeId })

  const query = useSuspenseQuery({
    ...(getNodeSuspenseQueryOptionsHook({ nodeId }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetNode404 | GetNode500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}