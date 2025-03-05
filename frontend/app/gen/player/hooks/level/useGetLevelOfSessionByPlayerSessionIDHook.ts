import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type {
  GetLevelOfSessionByPlayerSessionIDQueryResponse,
  GetLevelOfSessionByPlayerSessionIDPathParams,
  GetLevelOfSessionByPlayerSessionID500,
} from '../../models/GetLevelOfSessionByPlayerSessionID.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getLevelOfSessionByPlayerSessionIDQueryKey = ({
  player_session_id,
}: {
  player_session_id: GetLevelOfSessionByPlayerSessionIDPathParams['player_session_id']
}) => ['v1', { url: '/level/:player_session_id', params: { player_session_id: player_session_id } }] as const

export type GetLevelOfSessionByPlayerSessionIDQueryKey = ReturnType<typeof getLevelOfSessionByPlayerSessionIDQueryKey>

/**
 * @description Get the current level of the escape-room instance
 * @summary Get the current level of the escape-room instance
 * {@link /level/:player_session_id}
 */
export async function getLevelOfSessionByPlayerSessionIDHook(
  { player_session_id }: { player_session_id: GetLevelOfSessionByPlayerSessionIDPathParams['player_session_id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelOfSessionByPlayerSessionIDQueryResponse, ResponseErrorConfig<GetLevelOfSessionByPlayerSessionID500>, unknown>({
    method: 'GET',
    url: `/level/${player_session_id}`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/player-api/v1',
    ...requestConfig,
  })
  return res.data
}

export function getLevelOfSessionByPlayerSessionIDQueryOptionsHook(
  { player_session_id }: { player_session_id: GetLevelOfSessionByPlayerSessionIDPathParams['player_session_id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelOfSessionByPlayerSessionIDQueryKey({ player_session_id })
  return queryOptions<
    GetLevelOfSessionByPlayerSessionIDQueryResponse,
    ResponseErrorConfig<GetLevelOfSessionByPlayerSessionID500>,
    GetLevelOfSessionByPlayerSessionIDQueryResponse,
    typeof queryKey
  >({
    enabled: !!player_session_id,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelOfSessionByPlayerSessionIDHook({ player_session_id }, config)
    },
  })
}

/**
 * @description Get the current level of the escape-room instance
 * @summary Get the current level of the escape-room instance
 * {@link /level/:player_session_id}
 */
export function useGetLevelOfSessionByPlayerSessionIDHook<
  TData = GetLevelOfSessionByPlayerSessionIDQueryResponse,
  TQueryData = GetLevelOfSessionByPlayerSessionIDQueryResponse,
  TQueryKey extends QueryKey = GetLevelOfSessionByPlayerSessionIDQueryKey,
>(
  { player_session_id }: { player_session_id: GetLevelOfSessionByPlayerSessionIDPathParams['player_session_id'] },
  options: {
    query?: Partial<
      QueryObserverOptions<
        GetLevelOfSessionByPlayerSessionIDQueryResponse,
        ResponseErrorConfig<GetLevelOfSessionByPlayerSessionID500>,
        TData,
        TQueryData,
        TQueryKey
      >
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelOfSessionByPlayerSessionIDQueryKey({ player_session_id })

  const query = useQuery({
    ...(getLevelOfSessionByPlayerSessionIDQueryOptionsHook({ player_session_id }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetLevelOfSessionByPlayerSessionID500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}