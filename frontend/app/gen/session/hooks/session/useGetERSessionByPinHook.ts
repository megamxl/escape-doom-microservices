import client from '@kubb/plugin-client/clients/axios'
import type { GetERSessionByPinQueryResponse, GetERSessionByPinPathParams, GetERSessionByPin404 } from '../../models/GetERSessionByPin.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getERSessionByPinQueryKey = ({ pin }: { pin: GetERSessionByPinPathParams['pin'] }) =>
  ['v1', { url: '/session/:pin', params: { pin: pin } }] as const

export type GetERSessionByPinQueryKey = ReturnType<typeof getERSessionByPinQueryKey>

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Get escape-room session by room pin
 * {@link /session/:pin}
 */
export async function getERSessionByPinHook(
  { pin }: { pin: GetERSessionByPinPathParams['pin'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERSessionByPinQueryResponse, ResponseErrorConfig<GetERSessionByPin404>, unknown>({
    method: 'GET',
    url: `/session/${pin}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getERSessionByPinQueryOptionsHook({ pin }: { pin: GetERSessionByPinPathParams['pin'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getERSessionByPinQueryKey({ pin })
  return queryOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<GetERSessionByPin404>, GetERSessionByPinQueryResponse, typeof queryKey>({
    enabled: !!pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERSessionByPinHook({ pin }, config)
    },
  })
}

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Get escape-room session by room pin
 * {@link /session/:pin}
 */
export function useGetERSessionByPinHook<
  TData = GetERSessionByPinQueryResponse,
  TQueryData = GetERSessionByPinQueryResponse,
  TQueryKey extends QueryKey = GetERSessionByPinQueryKey,
>(
  { pin }: { pin: GetERSessionByPinPathParams['pin'] },
  options: {
    query?: Partial<QueryObserverOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<GetERSessionByPin404>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERSessionByPinQueryKey({ pin })

  const query = useQuery({
    ...(getERSessionByPinQueryOptionsHook({ pin }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetERSessionByPin404>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}