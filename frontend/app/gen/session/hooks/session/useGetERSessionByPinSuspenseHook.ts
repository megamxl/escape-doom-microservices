import client from '@kubb/plugin-client/clients/axios'
import type { GetERSessionByPinQueryResponse, GetERSessionByPinPathParams, GetERSessionByPin404 } from '../../models/GetERSessionByPin.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getERSessionByPinSuspenseQueryKey = ({ pin }: { pin: GetERSessionByPinPathParams['pin'] }) =>
  ['v1', { url: '/session/:pin', params: { pin: pin } }] as const

export type GetERSessionByPinSuspenseQueryKey = ReturnType<typeof getERSessionByPinSuspenseQueryKey>

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Get escape-room session by room pin
 * {@link /session/:pin}
 */
export async function getERSessionByPinSuspenseHook(
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

export function getERSessionByPinSuspenseQueryOptionsHook({ pin }: { pin: GetERSessionByPinPathParams['pin'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getERSessionByPinSuspenseQueryKey({ pin })
  return queryOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<GetERSessionByPin404>, GetERSessionByPinQueryResponse, typeof queryKey>({
    enabled: !!pin,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERSessionByPinSuspenseHook({ pin }, config)
    },
  })
}

/**
 * @description Fetches an escape-room session using its room pin
 * @summary Get escape-room session by room pin
 * {@link /session/:pin}
 */
export function useGetERSessionByPinSuspenseHook<
  TData = GetERSessionByPinQueryResponse,
  TQueryData = GetERSessionByPinQueryResponse,
  TQueryKey extends QueryKey = GetERSessionByPinSuspenseQueryKey,
>(
  { pin }: { pin: GetERSessionByPinPathParams['pin'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetERSessionByPinQueryResponse, ResponseErrorConfig<GetERSessionByPin404>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERSessionByPinSuspenseQueryKey({ pin })

  const query = useSuspenseQuery({
    ...(getERSessionByPinSuspenseQueryOptionsHook({ pin }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetERSessionByPin404>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}