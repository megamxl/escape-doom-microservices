import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetEscapeRoomSessionsQueryResponse, GetEscapeRoomSessionsQueryParams, GetEscapeRoomSessions404 } from '../../models/GetEscapeRoomSessions.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getEscapeRoomSessionsQueryKey = (params?: GetEscapeRoomSessionsQueryParams) =>
  ['v1', { url: '/escape-room-sessions' }, ...(params ? [params] : [])] as const

export type GetEscapeRoomSessionsQueryKey = ReturnType<typeof getEscapeRoomSessionsQueryKey>

/**
 * @description Retrieve leaderboards for all sessions, optionally filtered by session tags.
 * @summary Get all session leaderboards
 * {@link /escape-room-sessions}
 */
export async function getEscapeRoomSessionsHook(params?: GetEscapeRoomSessionsQueryParams, config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetEscapeRoomSessionsQueryResponse, ResponseErrorConfig<GetEscapeRoomSessions404>, unknown>({
    method: 'GET',
    url: `/escape-room-sessions`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/leaderboard-api/v1`,
    params,
    ...requestConfig,
  })
  return res.data
}

export function getEscapeRoomSessionsQueryOptionsHook(params?: GetEscapeRoomSessionsQueryParams, config: Partial<RequestConfig> = {}) {
  const queryKey = getEscapeRoomSessionsQueryKey(params)
  return queryOptions<GetEscapeRoomSessionsQueryResponse, ResponseErrorConfig<GetEscapeRoomSessions404>, GetEscapeRoomSessionsQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getEscapeRoomSessionsHook(params, config)
    },
  })
}

/**
 * @description Retrieve leaderboards for all sessions, optionally filtered by session tags.
 * @summary Get all session leaderboards
 * {@link /escape-room-sessions}
 */
export function useGetEscapeRoomSessionsHook<
  TData = GetEscapeRoomSessionsQueryResponse,
  TQueryData = GetEscapeRoomSessionsQueryResponse,
  TQueryKey extends QueryKey = GetEscapeRoomSessionsQueryKey,
>(
  params?: GetEscapeRoomSessionsQueryParams,
  options: {
    query?: Partial<QueryObserverOptions<GetEscapeRoomSessionsQueryResponse, ResponseErrorConfig<GetEscapeRoomSessions404>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getEscapeRoomSessionsQueryKey(params)

  const query = useQuery({
    ...(getEscapeRoomSessionsQueryOptionsHook(params, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetEscapeRoomSessions404>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}