import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetSceneQueryResponse, GetScenePathParams, GetScene404, GetScene500 } from '../../models/GetScene.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getSceneSuspenseQueryKey = ({ escapeRoomSceneId }: { escapeRoomSceneId: GetScenePathParams['escape-room-scene-id'] }) =>
  ['v1', { url: '/scenes/:escape-room-scene-id', params: { escapeRoomSceneId: escapeRoomSceneId } }] as const

export type GetSceneSuspenseQueryKey = ReturnType<typeof getSceneSuspenseQueryKey>

/**
 * @description Retrieve details of a specific scene by its ID
 * @summary Get details of a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export async function getSceneSuspenseHook(
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

export function getSceneSuspenseQueryOptionsHook(
  { escapeRoomSceneId }: { escapeRoomSceneId: GetScenePathParams['escape-room-scene-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getSceneSuspenseQueryKey({ escapeRoomSceneId })
  return queryOptions<GetSceneQueryResponse, ResponseErrorConfig<GetScene404 | GetScene500>, GetSceneQueryResponse, typeof queryKey>({
    enabled: !!escapeRoomSceneId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getSceneSuspenseHook({ escapeRoomSceneId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific scene by its ID
 * @summary Get details of a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export function useGetSceneSuspenseHook<
  TData = GetSceneQueryResponse,
  TQueryData = GetSceneQueryResponse,
  TQueryKey extends QueryKey = GetSceneSuspenseQueryKey,
>(
  { escapeRoomSceneId }: { escapeRoomSceneId: GetScenePathParams['escape-room-scene-id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetSceneQueryResponse, ResponseErrorConfig<GetScene404 | GetScene500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getSceneSuspenseQueryKey({ escapeRoomSceneId })

  const query = useSuspenseQuery({
    ...(getSceneSuspenseQueryOptionsHook({ escapeRoomSceneId }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetScene404 | GetScene500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}