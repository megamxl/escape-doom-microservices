import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetRoomPinQueryResponse, GetRoomPinPathParams, GetRoomPin404 } from '../../models/GetRoomPin.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getRoomPinSuspenseQueryKey = ({ room_pin }: { room_pin: GetRoomPinPathParams['room_pin'] }) =>
  ['v1', { url: '/:room_pin', params: { room_pin: room_pin } }] as const

export type GetRoomPinSuspenseQueryKey = ReturnType<typeof getRoomPinSuspenseQueryKey>

/**
 * @description Retrieve the leaderboard for a specific escape room session, showing progress for all users.
 * @summary Get current session leaderboard
 * {@link /:room_pin}
 */
export async function getRoomPinSuspenseHook(
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

export function getRoomPinSuspenseQueryOptionsHook({ room_pin }: { room_pin: GetRoomPinPathParams['room_pin'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getRoomPinSuspenseQueryKey({ room_pin })
  return queryOptions<GetRoomPinQueryResponse, ResponseErrorConfig<GetRoomPin404>, GetRoomPinQueryResponse, typeof queryKey>({
    enabled: !!room_pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getRoomPinSuspenseHook({ room_pin }, config)
    },
  })
}

/**
 * @description Retrieve the leaderboard for a specific escape room session, showing progress for all users.
 * @summary Get current session leaderboard
 * {@link /:room_pin}
 */
export function useGetRoomPinSuspenseHook<
  TData = GetRoomPinQueryResponse,
  TQueryData = GetRoomPinQueryResponse,
  TQueryKey extends QueryKey = GetRoomPinSuspenseQueryKey,
>(
  { room_pin }: { room_pin: GetRoomPinPathParams['room_pin'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetRoomPinQueryResponse, ResponseErrorConfig<GetRoomPin404>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getRoomPinSuspenseQueryKey({ room_pin })

  const query = useSuspenseQuery({
    ...(getRoomPinSuspenseQueryOptionsHook({ room_pin }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetRoomPin404>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}