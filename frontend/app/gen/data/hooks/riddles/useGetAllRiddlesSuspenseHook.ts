import client from '@kubb/plugin-client/clients/axios'
import type { GetAllRiddlesQueryResponse, GetAllRiddles500 } from '../../models/GetAllRiddles.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getAllRiddlesSuspenseQueryKey = () => ['v1', { url: '/riddles' }] as const

export type GetAllRiddlesSuspenseQueryKey = ReturnType<typeof getAllRiddlesSuspenseQueryKey>

/**
 * @description Retrieve all riddles that are not yet linked to any level
 * @summary Get all  riddles
 * {@link /riddles}
 */
export async function getAllRiddlesSuspenseHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllRiddlesQueryResponse, ResponseErrorConfig<GetAllRiddles500>, unknown>({
    method: 'GET',
    url: `/riddles`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllRiddlesSuspenseQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllRiddlesSuspenseQueryKey()
  return queryOptions<GetAllRiddlesQueryResponse, ResponseErrorConfig<GetAllRiddles500>, GetAllRiddlesQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllRiddlesSuspenseHook(config)
    },
  })
}

/**
 * @description Retrieve all riddles that are not yet linked to any level
 * @summary Get all  riddles
 * {@link /riddles}
 */
export function useGetAllRiddlesSuspenseHook<
  TData = GetAllRiddlesQueryResponse,
  TQueryData = GetAllRiddlesQueryResponse,
  TQueryKey extends QueryKey = GetAllRiddlesSuspenseQueryKey,
>(
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetAllRiddlesQueryResponse, ResponseErrorConfig<GetAllRiddles500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllRiddlesSuspenseQueryKey()

  const query = useSuspenseQuery({
    ...(getAllRiddlesSuspenseQueryOptionsHook(config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetAllRiddles500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}