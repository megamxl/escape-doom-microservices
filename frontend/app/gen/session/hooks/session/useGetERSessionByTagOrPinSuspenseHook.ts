import client from '@kubb/plugin-client/clients/axios'
import type { GetERSessionByTagOrPinQueryResponse, GetERSessionByTagOrPinQueryParams } from '../../models/GetERSessionByTagOrPin.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getERSessionByTagOrPinSuspenseQueryKey = (params?: GetERSessionByTagOrPinQueryParams) =>
  ['v1', { url: '/session' }, ...(params ? [params] : [])] as const

export type GetERSessionByTagOrPinSuspenseQueryKey = ReturnType<typeof getERSessionByTagOrPinSuspenseQueryKey>

/**
 * @description Retrieves escape-room sessions filtered by a tag or a specific 6-digit room pin. Only one filter (tag or pin) should be used per request.
 * @summary Get escape-room sessions by tag or pin
 * {@link /session}
 */
export async function getERSessionByTagOrPinSuspenseHook(
  params?: GetERSessionByTagOrPinQueryParams,
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetERSessionByTagOrPinQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/session`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1`,
    params,
    ...requestConfig,
  })
  return res.data
}

export function getERSessionByTagOrPinSuspenseQueryOptionsHook(params?: GetERSessionByTagOrPinQueryParams, config: Partial<RequestConfig> = {}) {
  const queryKey = getERSessionByTagOrPinSuspenseQueryKey(params)
  return queryOptions<GetERSessionByTagOrPinQueryResponse, ResponseErrorConfig<Error>, GetERSessionByTagOrPinQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERSessionByTagOrPinSuspenseHook(params, config)
    },
  })
}

/**
 * @description Retrieves escape-room sessions filtered by a tag or a specific 6-digit room pin. Only one filter (tag or pin) should be used per request.
 * @summary Get escape-room sessions by tag or pin
 * {@link /session}
 */
export function useGetERSessionByTagOrPinSuspenseHook<
  TData = GetERSessionByTagOrPinQueryResponse,
  TQueryData = GetERSessionByTagOrPinQueryResponse,
  TQueryKey extends QueryKey = GetERSessionByTagOrPinSuspenseQueryKey,
>(
  params?: GetERSessionByTagOrPinQueryParams,
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetERSessionByTagOrPinQueryResponse, ResponseErrorConfig<Error>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERSessionByTagOrPinSuspenseQueryKey(params)

  const query = useSuspenseQuery({
    ...(getERSessionByTagOrPinSuspenseQueryOptionsHook(params, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}