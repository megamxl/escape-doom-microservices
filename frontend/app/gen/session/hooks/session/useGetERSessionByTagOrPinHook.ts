import client from '@kubb/plugin-client/clients/axios'
import type { GetERSessionByTagOrPinQueryResponse, GetERSessionByTagOrPinQueryParams } from '../../models/GetERSessionByTagOrPin.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getERSessionByTagOrPinQueryKey = (params?: GetERSessionByTagOrPinQueryParams) => ['v1', { url: '/session' }, ...(params ? [params] : [])] as const

export type GetERSessionByTagOrPinQueryKey = ReturnType<typeof getERSessionByTagOrPinQueryKey>

/**
 * @description Retrieves escape-room sessions filtered by a tag or a specific 6-digit room pin. Only one filter (tag or pin) should be used per request.
 * @summary Get escape-room sessions by tag or pin
 * {@link /session}
 */
export async function getERSessionByTagOrPinHook(params?: GetERSessionByTagOrPinQueryParams, config: Partial<RequestConfig> & { client?: typeof client } = {}) {
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

export function getERSessionByTagOrPinQueryOptionsHook(params?: GetERSessionByTagOrPinQueryParams, config: Partial<RequestConfig> = {}) {
  const queryKey = getERSessionByTagOrPinQueryKey(params)
  return queryOptions<GetERSessionByTagOrPinQueryResponse, ResponseErrorConfig<Error>, GetERSessionByTagOrPinQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getERSessionByTagOrPinHook(params, config)
    },
  })
}

/**
 * @description Retrieves escape-room sessions filtered by a tag or a specific 6-digit room pin. Only one filter (tag or pin) should be used per request.
 * @summary Get escape-room sessions by tag or pin
 * {@link /session}
 */
export function useGetERSessionByTagOrPinHook<
  TData = GetERSessionByTagOrPinQueryResponse,
  TQueryData = GetERSessionByTagOrPinQueryResponse,
  TQueryKey extends QueryKey = GetERSessionByTagOrPinQueryKey,
>(
  params?: GetERSessionByTagOrPinQueryParams,
  options: {
    query?: Partial<QueryObserverOptions<GetERSessionByTagOrPinQueryResponse, ResponseErrorConfig<Error>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getERSessionByTagOrPinQueryKey(params)

  const query = useQuery({
    ...(getERSessionByTagOrPinQueryOptionsHook(params, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}