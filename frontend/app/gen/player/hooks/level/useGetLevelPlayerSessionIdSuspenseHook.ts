import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type {
  GetLevelPlayerSessionIdQueryResponse,
  GetLevelPlayerSessionIdPathParams,
  GetLevelPlayerSessionId500,
} from '../../models/GetLevelPlayerSessionId.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getLevelPlayerSessionIdSuspenseQueryKey = ({ player_session_id }: { player_session_id: GetLevelPlayerSessionIdPathParams['player_session_id'] }) =>
  ['v1', { url: '/level/:player_session_id', params: { player_session_id: player_session_id } }] as const

export type GetLevelPlayerSessionIdSuspenseQueryKey = ReturnType<typeof getLevelPlayerSessionIdSuspenseQueryKey>

/**
 * @description Get the current level of the escape-room instance
 * @summary Get the current level of the escape-room instance
 * {@link /level/:player_session_id}
 */
export async function getLevelPlayerSessionIdSuspenseHook(
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

export function getLevelPlayerSessionIdSuspenseQueryOptionsHook(
  { player_session_id }: { player_session_id: GetLevelPlayerSessionIdPathParams['player_session_id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelPlayerSessionIdSuspenseQueryKey({ player_session_id })
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
      return getLevelPlayerSessionIdSuspenseHook({ player_session_id }, config)
    },
  })
}

/**
 * @description Get the current level of the escape-room instance
 * @summary Get the current level of the escape-room instance
 * {@link /level/:player_session_id}
 */
export function useGetLevelPlayerSessionIdSuspenseHook<
  TData = GetLevelPlayerSessionIdQueryResponse,
  TQueryData = GetLevelPlayerSessionIdQueryResponse,
  TQueryKey extends QueryKey = GetLevelPlayerSessionIdSuspenseQueryKey,
>(
  { player_session_id }: { player_session_id: GetLevelPlayerSessionIdPathParams['player_session_id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetLevelPlayerSessionIdQueryResponse, ResponseErrorConfig<GetLevelPlayerSessionId500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelPlayerSessionIdSuspenseQueryKey({ player_session_id })

  const query = useSuspenseQuery({
    ...(getLevelPlayerSessionIdSuspenseQueryOptionsHook({ player_session_id }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetLevelPlayerSessionId500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}