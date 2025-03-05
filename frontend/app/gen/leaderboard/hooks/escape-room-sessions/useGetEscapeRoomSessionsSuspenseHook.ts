import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetEscapeRoomSessionsQueryResponse, GetEscapeRoomSessionsQueryParams, GetEscapeRoomSessions404 } from '../../models/GetEscapeRoomSessions.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getEscapeRoomSessionsSuspenseQueryKey = (params?: GetEscapeRoomSessionsQueryParams) =>
  ['v1', { url: '/escape-room-sessions' }, ...(params ? [params] : [])] as const

export type GetEscapeRoomSessionsSuspenseQueryKey = ReturnType<typeof getEscapeRoomSessionsSuspenseQueryKey>

/**
 * @description Retrieve leaderboards for all sessions, optionally filtered by session tags.
 * @summary Get all session leaderboards
 * {@link /escape-room-sessions}
 */
export async function getEscapeRoomSessionsSuspenseHook(
  params?: GetEscapeRoomSessionsQueryParams,
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
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

export function getEscapeRoomSessionsSuspenseQueryOptionsHook(params?: GetEscapeRoomSessionsQueryParams, config: Partial<RequestConfig> = {}) {
  const queryKey = getEscapeRoomSessionsSuspenseQueryKey(params)
  return queryOptions<GetEscapeRoomSessionsQueryResponse, ResponseErrorConfig<GetEscapeRoomSessions404>, GetEscapeRoomSessionsQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getEscapeRoomSessionsSuspenseHook(params, config)
    },
  })
}

/**
 * @description Retrieve leaderboards for all sessions, optionally filtered by session tags.
 * @summary Get all session leaderboards
 * {@link /escape-room-sessions}
 */
export function useGetEscapeRoomSessionsSuspenseHook<
  TData = GetEscapeRoomSessionsQueryResponse,
  TQueryData = GetEscapeRoomSessionsQueryResponse,
  TQueryKey extends QueryKey = GetEscapeRoomSessionsSuspenseQueryKey,
>(
  params?: GetEscapeRoomSessionsQueryParams,
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetEscapeRoomSessionsQueryResponse, ResponseErrorConfig<GetEscapeRoomSessions404>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getEscapeRoomSessionsSuspenseQueryKey(params)

  const query = useSuspenseQuery({
    ...(getEscapeRoomSessionsSuspenseQueryOptionsHook(params, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetEscapeRoomSessions404>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}