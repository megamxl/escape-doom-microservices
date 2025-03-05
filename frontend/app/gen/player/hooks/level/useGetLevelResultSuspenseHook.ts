import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetLevelResultQueryResponse, GetLevelResultPathParams } from '../../models/GetLevelResult.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getLevelResultSuspenseQueryKey = ({ player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] }) =>
  ['v1', { url: '/level/:player_session_id/result', params: { player_session_id: player_session_id } }] as const

export type GetLevelResultSuspenseQueryKey = ReturnType<typeof getLevelResultSuspenseQueryKey>

/**
 * @description Get the result of the submitted solution for the current level of the escape-room instance
 * @summary Get the result of the submitted solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/result}
 */
export async function getLevelResultSuspenseHook(
  { player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelResultQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/level/${player_session_id}/result`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/player-api/v1',
    ...requestConfig,
  })
  return res.data
}

export function getLevelResultSuspenseQueryOptionsHook(
  { player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelResultSuspenseQueryKey({ player_session_id })
  return queryOptions<GetLevelResultQueryResponse, ResponseErrorConfig<Error>, GetLevelResultQueryResponse, typeof queryKey>({
    enabled: !!player_session_id,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelResultSuspenseHook({ player_session_id }, config)
    },
  })
}

/**
 * @description Get the result of the submitted solution for the current level of the escape-room instance
 * @summary Get the result of the submitted solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/result}
 */
export function useGetLevelResultSuspenseHook<
  TData = GetLevelResultQueryResponse,
  TQueryData = GetLevelResultQueryResponse,
  TQueryKey extends QueryKey = GetLevelResultSuspenseQueryKey,
>(
  { player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetLevelResultQueryResponse, ResponseErrorConfig<Error>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelResultSuspenseQueryKey({ player_session_id })

  const query = useSuspenseQuery({
    ...(getLevelResultSuspenseQueryOptionsHook({ player_session_id }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}