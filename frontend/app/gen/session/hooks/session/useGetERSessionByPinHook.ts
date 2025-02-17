import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetERSessionByPinQueryResponse, GetERSessionByPinPathParams } from '../../models/GetERSessionByPin.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getERSessionByPinQueryKey = ({ room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] }) =>
  ['v1', { url: '/session/:room_pin', params: { room_pin: room_pin } }] as const

export type GetERSessionByPinQueryKey = ReturnType<typeof getERSessionByPinQueryKey>

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Retrieve an escape-room session by room pin
 * {@link /session/:room_pin}
 */
export async function getERSessionByPinHook(
  { room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERSessionByPinQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/session/${room_pin}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getERSessionByPinQueryOptionsHook({ room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getERSessionByPinQueryKey({ room_pin })
  return queryOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<Error>, GetERSessionByPinQueryResponse, typeof queryKey>({
    enabled: !!room_pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERSessionByPinHook({ room_pin }, config)
    },
  })
}

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Retrieve an escape-room session by room pin
 * {@link /session/:room_pin}
 */
export function useGetERSessionByPinHook<
  TData = GetERSessionByPinQueryResponse,
  TQueryData = GetERSessionByPinQueryResponse,
  TQueryKey extends QueryKey = GetERSessionByPinQueryKey,
>(
  { room_pin }: { room_pin: GetERSessionByPinPathParams['room_pin'] },
  options: {
    query?: Partial<QueryObserverOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<Error>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERSessionByPinQueryKey({ room_pin })

  const query = useQuery({
    ...(getERSessionByPinQueryOptionsHook({ room_pin }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}