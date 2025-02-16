import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetAllScenesQueryResponse, GetAllScenes500 } from '../../models/GetAllScenes.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getAllScenesSuspenseQueryKey = () => ['v1', { url: '/scene' }] as const

export type GetAllScenesSuspenseQueryKey = ReturnType<typeof getAllScenesSuspenseQueryKey>

/**
 * @description Retrieve all scenes that are not linked to any specific level
 * @summary Get all scenes
 * {@link /scene}
 */
export async function getAllScenesSuspenseHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllScenesQueryResponse, ResponseErrorConfig<GetAllScenes500>, unknown>({
    method: 'GET',
    url: `/scene`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllScenesSuspenseQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllScenesSuspenseQueryKey()
  return queryOptions<GetAllScenesQueryResponse, ResponseErrorConfig<GetAllScenes500>, GetAllScenesQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllScenesSuspenseHook(config)
    },
  })
}

/**
 * @description Retrieve all scenes that are not linked to any specific level
 * @summary Get all scenes
 * {@link /scene}
 */
export function useGetAllScenesSuspenseHook<
  TData = GetAllScenesQueryResponse,
  TQueryData = GetAllScenesQueryResponse,
  TQueryKey extends QueryKey = GetAllScenesSuspenseQueryKey,
>(
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetAllScenesQueryResponse, ResponseErrorConfig<GetAllScenes500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllScenesSuspenseQueryKey()

  const query = useSuspenseQuery({
    ...(getAllScenesSuspenseQueryOptionsHook(config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetAllScenes500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}