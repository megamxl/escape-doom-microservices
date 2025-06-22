import client from '@kubb/plugin-client/clients/axios'
import type { GetAllScenesQueryResponse, GetAllScenes500 } from '../../models/GetAllScenes.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getAllScenesQueryKey = () => ['v1', { url: '/scenes' }] as const

export type GetAllScenesQueryKey = ReturnType<typeof getAllScenesQueryKey>

/**
 * @description Retrieve all scenes that are not linked to any specific level
 * @summary Get all scenes
 * {@link /scenes}
 */
export async function getAllScenesHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllScenesQueryResponse, ResponseErrorConfig<GetAllScenes500>, unknown>({
    method: 'GET',
    url: `/scenes`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllScenesQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllScenesQueryKey()
  return queryOptions<GetAllScenesQueryResponse, ResponseErrorConfig<GetAllScenes500>, GetAllScenesQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllScenesHook(config)
    },
  })
}

/**
 * @description Retrieve all scenes that are not linked to any specific level
 * @summary Get all scenes
 * {@link /scenes}
 */
export function useGetAllScenesHook<
  TData = GetAllScenesQueryResponse,
  TQueryData = GetAllScenesQueryResponse,
  TQueryKey extends QueryKey = GetAllScenesQueryKey,
>(
  options: {
    query?: Partial<QueryObserverOptions<GetAllScenesQueryResponse, ResponseErrorConfig<GetAllScenes500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllScenesQueryKey()

  const query = useQuery({
    ...(getAllScenesQueryOptionsHook(config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetAllScenes500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}