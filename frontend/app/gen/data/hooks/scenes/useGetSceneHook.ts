import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetSceneQueryResponse, GetScenePathParams, GetScene404, GetScene500 } from '../../models/GetScene.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getSceneQueryKey = ({ escapeRoomSceneId }: { escapeRoomSceneId: GetScenePathParams['escape-room-scene-id'] }) =>
  ['v1', { url: '/scenes/:escape-room-scene-id', params: { escapeRoomSceneId: escapeRoomSceneId } }] as const

export type GetSceneQueryKey = ReturnType<typeof getSceneQueryKey>

/**
 * @description Retrieve details of a specific scene by its ID
 * @summary Get details of a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export async function getSceneHook(
  { escapeRoomSceneId }: { escapeRoomSceneId: GetScenePathParams['escape-room-scene-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetSceneQueryResponse, ResponseErrorConfig<GetScene404 | GetScene500>, unknown>({
    method: 'GET',
    url: `/scenes/${escapeRoomSceneId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getSceneQueryOptionsHook(
  { escapeRoomSceneId }: { escapeRoomSceneId: GetScenePathParams['escape-room-scene-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getSceneQueryKey({ escapeRoomSceneId })
  return queryOptions<GetSceneQueryResponse, ResponseErrorConfig<GetScene404 | GetScene500>, GetSceneQueryResponse, typeof queryKey>({
    enabled: !!escapeRoomSceneId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getSceneHook({ escapeRoomSceneId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific scene by its ID
 * @summary Get details of a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export function useGetSceneHook<TData = GetSceneQueryResponse, TQueryData = GetSceneQueryResponse, TQueryKey extends QueryKey = GetSceneQueryKey>(
  { escapeRoomSceneId }: { escapeRoomSceneId: GetScenePathParams['escape-room-scene-id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetSceneQueryResponse, ResponseErrorConfig<GetScene404 | GetScene500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getSceneQueryKey({ escapeRoomSceneId })

  const query = useQuery({
    ...(getSceneQueryOptionsHook({ escapeRoomSceneId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetScene404 | GetScene500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}