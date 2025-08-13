import client from '@kubb/plugin-client/clients/axios'
import type { GetAllLevelsQueryResponse, GetAllLevels500 } from '../../models/GetAllLevels.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getAllLevelsQueryKey = () => ['v1', { url: '/levels' }] as const

export type GetAllLevelsQueryKey = ReturnType<typeof getAllLevelsQueryKey>

/**
 * @description Retrieve all levels by a specific user
 * @summary Retrieve all levels by a specific user
 * {@link /levels}
 */
export async function getAllLevelsHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllLevelsQueryResponse, ResponseErrorConfig<GetAllLevels500>, unknown>({
    method: 'GET',
    url: `/levels`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllLevelsQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllLevelsQueryKey()
  return queryOptions<GetAllLevelsQueryResponse, ResponseErrorConfig<GetAllLevels500>, GetAllLevelsQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllLevelsHook(config)
    },
  })
}

/**
 * @description Retrieve all levels by a specific user
 * @summary Retrieve all levels by a specific user
 * {@link /levels}
 */
export function useGetAllLevelsHook<
  TData = GetAllLevelsQueryResponse,
  TQueryData = GetAllLevelsQueryResponse,
  TQueryKey extends QueryKey = GetAllLevelsQueryKey,
>(
  options: {
    query?: Partial<QueryObserverOptions<GetAllLevelsQueryResponse, ResponseErrorConfig<GetAllLevels500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllLevelsQueryKey()

  const query = useQuery({
    ...(getAllLevelsQueryOptionsHook(config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetAllLevels500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}