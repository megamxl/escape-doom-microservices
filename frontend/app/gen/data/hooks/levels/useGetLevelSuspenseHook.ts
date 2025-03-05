import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetLevelQueryResponse, GetLevelPathParams, GetLevel404, GetLevel500 } from '../../models/GetLevel.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getLevelSuspenseQueryKey = ({ escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] }) =>
  ['v1', { url: '/levels/:escape-room-level-id', params: { escapeRoomLevelId: escapeRoomLevelId } }] as const

export type GetLevelSuspenseQueryKey = ReturnType<typeof getLevelSuspenseQueryKey>

/**
 * @description Retrieve details of a specific EscapeRoomLevel by its ID
 * @summary Get details of a level
 * {@link /levels/:escape-room-level-id}
 */
export async function getLevelSuspenseHook(
  { escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelQueryResponse, ResponseErrorConfig<GetLevel404 | GetLevel500>, unknown>({
    method: 'GET',
    url: `/levels/${escapeRoomLevelId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getLevelSuspenseQueryOptionsHook(
  { escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelSuspenseQueryKey({ escapeRoomLevelId })
  return queryOptions<GetLevelQueryResponse, ResponseErrorConfig<GetLevel404 | GetLevel500>, GetLevelQueryResponse, typeof queryKey>({
    enabled: !!escapeRoomLevelId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelSuspenseHook({ escapeRoomLevelId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific EscapeRoomLevel by its ID
 * @summary Get details of a level
 * {@link /levels/:escape-room-level-id}
 */
export function useGetLevelSuspenseHook<
  TData = GetLevelQueryResponse,
  TQueryData = GetLevelQueryResponse,
  TQueryKey extends QueryKey = GetLevelSuspenseQueryKey,
>(
  { escapeRoomLevelId }: { escapeRoomLevelId: GetLevelPathParams['escape-room-level-id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetLevelQueryResponse, ResponseErrorConfig<GetLevel404 | GetLevel500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelSuspenseQueryKey({ escapeRoomLevelId })

  const query = useSuspenseQuery({
    ...(getLevelSuspenseQueryOptionsHook({ escapeRoomLevelId }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetLevel404 | GetLevel500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}