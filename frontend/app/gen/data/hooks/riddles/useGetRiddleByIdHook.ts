import client from '@kubb/plugin-client/clients/axios'
import type { GetRiddleByIdQueryResponse, GetRiddleByIdPathParams, GetRiddleById500 } from '../../models/GetRiddleById.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getRiddleByIdQueryKey = ({ riddleId }: { riddleId: GetRiddleByIdPathParams['riddle-id'] }) =>
  ['v1', { url: '/riddles/:riddle-id', params: { riddleId: riddleId } }] as const

export type GetRiddleByIdQueryKey = ReturnType<typeof getRiddleByIdQueryKey>

/**
 * @description Retrieve riddle that matches the UUID
 * @summary Get one riddle by id
 * {@link /riddles/:riddle-id}
 */
export async function getRiddleByIdHook(
  { riddleId }: { riddleId: GetRiddleByIdPathParams['riddle-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetRiddleByIdQueryResponse, ResponseErrorConfig<GetRiddleById500>, unknown>({
    method: 'GET',
    url: `/riddles/${riddleId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getRiddleByIdQueryOptionsHook({ riddleId }: { riddleId: GetRiddleByIdPathParams['riddle-id'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getRiddleByIdQueryKey({ riddleId })
  return queryOptions<GetRiddleByIdQueryResponse, ResponseErrorConfig<GetRiddleById500>, GetRiddleByIdQueryResponse, typeof queryKey>({
    enabled: !!riddleId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getRiddleByIdHook({ riddleId }, config)
    },
  })
}

/**
 * @description Retrieve riddle that matches the UUID
 * @summary Get one riddle by id
 * {@link /riddles/:riddle-id}
 */
export function useGetRiddleByIdHook<
  TData = GetRiddleByIdQueryResponse,
  TQueryData = GetRiddleByIdQueryResponse,
  TQueryKey extends QueryKey = GetRiddleByIdQueryKey,
>(
  { riddleId }: { riddleId: GetRiddleByIdPathParams['riddle-id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetRiddleByIdQueryResponse, ResponseErrorConfig<GetRiddleById500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getRiddleByIdQueryKey({ riddleId })

  const query = useQuery({
    ...(getRiddleByIdQueryOptionsHook({ riddleId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetRiddleById500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}