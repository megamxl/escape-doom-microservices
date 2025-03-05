import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetFullExportRoomPinQueryResponse, GetFullExportRoomPinPathParams } from '../../models/GetFullExportRoomPin.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getFullExportRoomPinQueryKey = ({ room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] }) =>
  ['v1', { url: '/full-export/:room_pin', params: { room_pin: room_pin } }] as const

export type GetFullExportRoomPinQueryKey = ReturnType<typeof getFullExportRoomPinQueryKey>

/**
 * @description Retrieve full leaderboard for a specific escape room session, showing progress for all users.
 * @summary Retrieve full leaderboard
 * {@link /full-export/:room_pin}
 */
export async function getFullExportRoomPinHook(
  { room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetFullExportRoomPinQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/full-export/${room_pin}`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/leaderboard-api/v1',
    ...requestConfig,
  })
  return res.data
}

export function getFullExportRoomPinQueryOptionsHook(
  { room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getFullExportRoomPinQueryKey({ room_pin })
  return queryOptions<GetFullExportRoomPinQueryResponse, ResponseErrorConfig<Error>, GetFullExportRoomPinQueryResponse, typeof queryKey>({
    enabled: !!room_pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getFullExportRoomPinHook({ room_pin }, config)
    },
  })
}

/**
 * @description Retrieve full leaderboard for a specific escape room session, showing progress for all users.
 * @summary Retrieve full leaderboard
 * {@link /full-export/:room_pin}
 */
export function useGetFullExportRoomPinHook<
  TData = GetFullExportRoomPinQueryResponse,
  TQueryData = GetFullExportRoomPinQueryResponse,
  TQueryKey extends QueryKey = GetFullExportRoomPinQueryKey,
>(
  { room_pin }: { room_pin: GetFullExportRoomPinPathParams['room_pin'] },
  options: {
    query?: Partial<QueryObserverOptions<GetFullExportRoomPinQueryResponse, ResponseErrorConfig<Error>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getFullExportRoomPinQueryKey({ room_pin })

  const query = useQuery({
    ...(getFullExportRoomPinQueryOptionsHook({ room_pin }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}