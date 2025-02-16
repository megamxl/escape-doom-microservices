import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetAllRiddlesQueryResponse, GetAllRiddles500 } from '../../models/GetAllRiddles.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getAllRiddlesQueryKey = () => ['v1', { url: '/all-riddles' }] as const

export type GetAllRiddlesQueryKey = ReturnType<typeof getAllRiddlesQueryKey>

/**
 * @description Retrieve all riddles that are not yet linked to any level
 * @summary Get all  riddles
 * {@link /all-riddles}
 */
export async function getAllRiddlesHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllRiddlesQueryResponse, ResponseErrorConfig<GetAllRiddles500>, unknown>({
    method: 'GET',
    url: `/all-riddles`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllRiddlesQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllRiddlesQueryKey()
  return queryOptions<GetAllRiddlesQueryResponse, ResponseErrorConfig<GetAllRiddles500>, GetAllRiddlesQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllRiddlesHook(config)
    },
  })
}

/**
 * @description Retrieve all riddles that are not yet linked to any level
 * @summary Get all  riddles
 * {@link /all-riddles}
 */
export function useGetAllRiddlesHook<
  TData = GetAllRiddlesQueryResponse,
  TQueryData = GetAllRiddlesQueryResponse,
  TQueryKey extends QueryKey = GetAllRiddlesQueryKey,
>(
  options: {
    query?: Partial<QueryObserverOptions<GetAllRiddlesQueryResponse, ResponseErrorConfig<GetAllRiddles500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllRiddlesQueryKey()

  const query = useQuery({
    ...(getAllRiddlesQueryOptionsHook(config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetAllRiddles500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}