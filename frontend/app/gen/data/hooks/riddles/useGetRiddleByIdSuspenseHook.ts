import client from '@kubb/plugin-client/clients/axios'
import type { GetRiddleByIdQueryResponse, GetRiddleByIdPathParams, GetRiddleById500 } from '../../models/GetRiddleById.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getRiddleByIdSuspenseQueryKey = ({ riddleId }: { riddleId: GetRiddleByIdPathParams['riddle-id'] }) =>
  ['v1', { url: '/riddles/:riddle-id', params: { riddleId: riddleId } }] as const

export type GetRiddleByIdSuspenseQueryKey = ReturnType<typeof getRiddleByIdSuspenseQueryKey>

/**
 * @description Retrieve riddle that matches the UUID
 * @summary Get one riddle by id
 * {@link /riddles/:riddle-id}
 */
export async function getRiddleByIdSuspenseHook(
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

export function getRiddleByIdSuspenseQueryOptionsHook({ riddleId }: { riddleId: GetRiddleByIdPathParams['riddle-id'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getRiddleByIdSuspenseQueryKey({ riddleId })
  return queryOptions<GetRiddleByIdQueryResponse, ResponseErrorConfig<GetRiddleById500>, GetRiddleByIdQueryResponse, typeof queryKey>({
    enabled: !!riddleId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getRiddleByIdSuspenseHook({ riddleId }, config)
    },
  })
}

/**
 * @description Retrieve riddle that matches the UUID
 * @summary Get one riddle by id
 * {@link /riddles/:riddle-id}
 */
export function useGetRiddleByIdSuspenseHook<
  TData = GetRiddleByIdQueryResponse,
  TQueryData = GetRiddleByIdQueryResponse,
  TQueryKey extends QueryKey = GetRiddleByIdSuspenseQueryKey,
>(
  { riddleId }: { riddleId: GetRiddleByIdPathParams['riddle-id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetRiddleByIdQueryResponse, ResponseErrorConfig<GetRiddleById500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getRiddleByIdSuspenseQueryKey({ riddleId })

  const query = useSuspenseQuery({
    ...(getRiddleByIdSuspenseQueryOptionsHook({ riddleId }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetRiddleById500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}