import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetERSessionByPinQueryResponse, GetERSessionByPinPathParams } from '../../models/GetERSessionByPin.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getERSessionByPinSuspenseQueryKey = ({ room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] }) =>
  ['v1', { url: '/session/:room_pin', params: { room_pin: room_pin } }] as const

export type GetERSessionByPinSuspenseQueryKey = ReturnType<typeof getERSessionByPinSuspenseQueryKey>

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Retrieve an escape-room session by room pin
 * {@link /session/:room_pin}
 */
export async function getERSessionByPinSuspenseHook(
  { room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERSessionByPinQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/session/${room_pin}`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1',
    ...requestConfig,
  })
  return res.data
}

export function getERSessionByPinSuspenseQueryOptionsHook(
  { room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getERSessionByPinSuspenseQueryKey({ room_pin })
  return queryOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<Error>, GetERSessionByPinQueryResponse, typeof queryKey>({
    enabled: !!room_pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERSessionByPinSuspenseHook({ room_pin }, config)
    },
  })
}

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Retrieve an escape-room session by room pin
 * {@link /session/:room_pin}
 */
export function useGetERSessionByPinSuspenseHook<
  TData = GetERSessionByPinQueryResponse,
  TQueryData = GetERSessionByPinQueryResponse,
  TQueryKey extends QueryKey = GetERSessionByPinSuspenseQueryKey,
>(
  { room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<Error>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERSessionByPinSuspenseQueryKey({ room_pin })

  const query = useSuspenseQuery({
    ...(getERSessionByPinSuspenseQueryOptionsHook({ room_pin }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}