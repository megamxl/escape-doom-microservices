import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetLevelResultQueryResponse, GetLevelResultPathParams } from '../../models/GetLevelResult.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getLevelResultQueryKey = ({ player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] }) =>
  ['v1', { url: '/level/:player_session_id/result', params: { player_session_id: player_session_id } }] as const

export type GetLevelResultQueryKey = ReturnType<typeof getLevelResultQueryKey>

/**
 * @description Get the result of the submitted solution for the current level of the escape-room instance
 * @summary Get the result of the submitted solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/result}
 */
export async function getLevelResultHook(
  { player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelResultQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/level/${player_session_id}/result`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/player-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getLevelResultQueryOptionsHook(
  { player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelResultQueryKey({ player_session_id })
  return queryOptions<GetLevelResultQueryResponse, ResponseErrorConfig<Error>, GetLevelResultQueryResponse, typeof queryKey>({
    enabled: !!player_session_id,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelResultHook({ player_session_id }, config)
    },
  })
}

/**
 * @description Get the result of the submitted solution for the current level of the escape-room instance
 * @summary Get the result of the submitted solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/result}
 */
export function useGetLevelResultHook<
  TData = GetLevelResultQueryResponse,
  TQueryData = GetLevelResultQueryResponse,
  TQueryKey extends QueryKey = GetLevelResultQueryKey,
>(
  { player_session_id }: { player_session_id: GetLevelResultPathParams['player_session_id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetLevelResultQueryResponse, ResponseErrorConfig<Error>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelResultQueryKey({ player_session_id })

  const query = useQuery({
    ...(getLevelResultQueryOptionsHook({ player_session_id }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}