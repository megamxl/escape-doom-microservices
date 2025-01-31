import client from '@kubb/plugin-client/clients/axios'
import type { GetFullExportRoomPinQueryResponse, GetFullExportRoomPinPathParams } from '../../models/GetFullExportRoomPin.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getFullExportRoomPinSuspenseQueryKey = ({ room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] }) =>
  ['v5', { url: '/full-export/:room_pin', params: { room_pin: room_pin } }] as const

export type GetFullExportRoomPinSuspenseQueryKey = ReturnType<typeof getFullExportRoomPinSuspenseQueryKey>

/**
 * @description Retrieve full leaderboard for a specific escape room session, showing progress for all users.
 * @summary Retrieve full leaderboard
 * {@link /full-export/:room_pin}
 */
export async function getFullExportRoomPinSuspenseHook(
  { room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetFullExportRoomPinQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/full-export/${room_pin}`,
    ...requestConfig,
  })
  return res.data
}

export function getFullExportRoomPinSuspenseQueryOptionsHook(
  { room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getFullExportRoomPinSuspenseQueryKey({ room_pin })
  return queryOptions<GetFullExportRoomPinQueryResponse, ResponseErrorConfig<Error>, GetFullExportRoomPinQueryResponse, typeof queryKey>({
    enabled: !!room_pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getFullExportRoomPinSuspenseHook({ room_pin }, config)
    },
  })
}

/**
 * @description Retrieve full leaderboard for a specific escape room session, showing progress for all users.
 * @summary Retrieve full leaderboard
 * {@link /full-export/:room_pin}
 */
export function useGetFullExportRoomPinSuspenseHook<
  TData = GetFullExportRoomPinQueryResponse,
  TQueryData = GetFullExportRoomPinQueryResponse,
  TQueryKey extends QueryKey = GetFullExportRoomPinSuspenseQueryKey,
>(
  { room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetFullExportRoomPinQueryResponse, ResponseErrorConfig<Error>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getFullExportRoomPinSuspenseQueryKey({ room_pin })

  const query = useSuspenseQuery({
    ...(getFullExportRoomPinSuspenseQueryOptionsHook({ room_pin }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}