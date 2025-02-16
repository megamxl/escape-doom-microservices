import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type {
  GetLevelPlayerSessionIdQueryResponse,
  GetLevelPlayerSessionIdPathParams,
  GetLevelPlayerSessionId500,
} from '../../models/GetLevelPlayerSessionId.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getLevelPlayerSessionIdQueryKey = ({ player_session_id }: { player_session_id: GetLevelPlayerSessionIdPathParams['player_session_id'] }) =>
  ['v1', { url: '/level/:player_session_id', params: { player_session_id: player_session_id } }] as const

export type GetLevelPlayerSessionIdQueryKey = ReturnType<typeof getLevelPlayerSessionIdQueryKey>

/**
 * @description Get the current level of the escape-room instance
 * @summary Get the current level of the escape-room instance
 * {@link /level/:player_session_id}
 */
export async function getLevelPlayerSessionIdHook(
  { player_session_id }: { player_session_id: GetLevelPlayerSessionIdPathParams['player_session_id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelPlayerSessionIdQueryResponse, ResponseErrorConfig<GetLevelPlayerSessionId500>, unknown>({
    method: 'GET',
    url: `/level/${player_session_id}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/player-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getLevelPlayerSessionIdQueryOptionsHook(
  { player_session_id }: { player_session_id: GetLevelPlayerSessionIdPathParams['player_session_id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelPlayerSessionIdQueryKey({ player_session_id })
  return queryOptions<
    GetLevelPlayerSessionIdQueryResponse,
    ResponseErrorConfig<GetLevelPlayerSessionId500>,
    GetLevelPlayerSessionIdQueryResponse,
    typeof queryKey
  >({
    enabled: !!player_session_id,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelPlayerSessionIdHook({ player_session_id }, config)
    },
  })
}

/**
 * @description Get the current level of the escape-room instance
 * @summary Get the current level of the escape-room instance
 * {@link /level/:player_session_id}
 */
export function useGetLevelPlayerSessionIdHook<
  TData = GetLevelPlayerSessionIdQueryResponse,
  TQueryData = GetLevelPlayerSessionIdQueryResponse,
  TQueryKey extends QueryKey = GetLevelPlayerSessionIdQueryKey,
>(
  { player_session_id }: { player_session_id: GetLevelPlayerSessionIdPathParams['player_session_id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetLevelPlayerSessionIdQueryResponse, ResponseErrorConfig<GetLevelPlayerSessionId500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelPlayerSessionIdQueryKey({ player_session_id })

  const query = useQuery({
    ...(getLevelPlayerSessionIdQueryOptionsHook({ player_session_id }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetLevelPlayerSessionId500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}