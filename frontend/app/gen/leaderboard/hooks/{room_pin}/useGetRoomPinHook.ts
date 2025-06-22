import client from '@kubb/plugin-client/clients/axios'
import type { GetRoomPinQueryResponse, GetRoomPinPathParams, GetRoomPin404 } from '../../models/GetRoomPin.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getRoomPinQueryKey = ({ room_pin }: { room_pin: GetRoomPinPathParams['room_pin'] }) =>
  ['v1', { url: '/:room_pin', params: { room_pin: room_pin } }] as const

export type GetRoomPinQueryKey = ReturnType<typeof getRoomPinQueryKey>

/**
 * @description Retrieve the leaderboard for a specific escape room session, showing progress for all users.
 * @summary Get current session leaderboard
 * {@link /:room_pin}
 */
export async function getRoomPinHook(
  { room_pin }: { room_pin: GetRoomPinPathParams['room_pin'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetRoomPinQueryResponse, ResponseErrorConfig<GetRoomPin404>, unknown>({
    method: 'GET',
    url: `/${room_pin}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/leaderboard-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getRoomPinQueryOptionsHook({ room_pin }: { room_pin: GetRoomPinPathParams['room_pin'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getRoomPinQueryKey({ room_pin })
  return queryOptions<GetRoomPinQueryResponse, ResponseErrorConfig<GetRoomPin404>, GetRoomPinQueryResponse, typeof queryKey>({
    enabled: !!room_pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getRoomPinHook({ room_pin }, config)
    },
  })
}

/**
 * @description Retrieve the leaderboard for a specific escape room session, showing progress for all users.
 * @summary Get current session leaderboard
 * {@link /:room_pin}
 */
export function useGetRoomPinHook<TData = GetRoomPinQueryResponse, TQueryData = GetRoomPinQueryResponse, TQueryKey extends QueryKey = GetRoomPinQueryKey>(
  { room_pin }: { room_pin: GetRoomPinPathParams['room_pin'] },
  options: {
    query?: Partial<QueryObserverOptions<GetRoomPinQueryResponse, ResponseErrorConfig<GetRoomPin404>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getRoomPinQueryKey({ room_pin })

  const query = useQuery({
    ...(getRoomPinQueryOptionsHook({ room_pin }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetRoomPin404>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}