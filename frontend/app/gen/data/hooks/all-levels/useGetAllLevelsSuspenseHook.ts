import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetAllLevelsQueryResponse, GetAllLevels500 } from '../../models/GetAllLevels.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getAllLevelsSuspenseQueryKey = () => ['v1', { url: '/all-levels' }] as const

export type GetAllLevelsSuspenseQueryKey = ReturnType<typeof getAllLevelsSuspenseQueryKey>

/**
 * @description Retrieve all levels by a specific user
 * @summary Retrieve all levels by a specific user
 * {@link /all-levels}
 */
export async function getAllLevelsSuspenseHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllLevelsQueryResponse, ResponseErrorConfig<GetAllLevels500>, unknown>({
    method: 'GET',
    url: `/all-levels`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllLevelsSuspenseQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllLevelsSuspenseQueryKey()
  return queryOptions<GetAllLevelsQueryResponse, ResponseErrorConfig<GetAllLevels500>, GetAllLevelsQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllLevelsSuspenseHook(config)
    },
  })
}

/**
 * @description Retrieve all levels by a specific user
 * @summary Retrieve all levels by a specific user
 * {@link /all-levels}
 */
export function useGetAllLevelsSuspenseHook<
  TData = GetAllLevelsQueryResponse,
  TQueryData = GetAllLevelsQueryResponse,
  TQueryKey extends QueryKey = GetAllLevelsSuspenseQueryKey,
>(
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetAllLevelsQueryResponse, ResponseErrorConfig<GetAllLevels500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllLevelsSuspenseQueryKey()

  const query = useSuspenseQuery({
    ...(getAllLevelsSuspenseQueryOptionsHook(config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetAllLevels500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}