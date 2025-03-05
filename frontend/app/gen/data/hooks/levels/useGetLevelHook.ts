import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetLevelQueryResponse, GetLevelPathParams, GetLevel404, GetLevel500 } from '../../models/GetLevel.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getLevelQueryKey = ({ escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] }) =>
  ['v1', { url: '/levels/:escape-room-level-id', params: { escapeRoomLevelId: escapeRoomLevelId } }] as const

export type GetLevelQueryKey = ReturnType<typeof getLevelQueryKey>

/**
 * @description Retrieve details of a specific EscapeRoomLevel by its ID
 * @summary Get details of a level
 * {@link /levels/:escape-room-level-id}
 */
export async function getLevelHook(
  { escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelQueryResponse, ResponseErrorConfig<GetLevel404 | GetLevel500>, unknown>({
    method: 'GET',
    url: `/levels/${escapeRoomLevelId}`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1',
    ...requestConfig,
  })
  return res.data
}

export function getLevelQueryOptionsHook(
  { escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelQueryKey({ escapeRoomLevelId })
  return queryOptions<GetLevelQueryResponse, ResponseErrorConfig<GetLevel404 | GetLevel500>, GetLevelQueryResponse, typeof queryKey>({
    enabled: !!escapeRoomLevelId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelHook({ escapeRoomLevelId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific EscapeRoomLevel by its ID
 * @summary Get details of a level
 * {@link /levels/:escape-room-level-id}
 */
export function useGetLevelHook<TData = GetLevelQueryResponse, TQueryData = GetLevelQueryResponse, TQueryKey extends QueryKey = GetLevelQueryKey>(
  { escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetLevelQueryResponse, ResponseErrorConfig<GetLevel404 | GetLevel500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelQueryKey({ escapeRoomLevelId })

  const query = useQuery({
    ...(getLevelQueryOptionsHook({ escapeRoomLevelId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetLevel404 | GetLevel500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}