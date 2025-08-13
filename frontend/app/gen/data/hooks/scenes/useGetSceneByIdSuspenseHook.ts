import client from '@kubb/plugin-client/clients/axios'
import type { GetSceneByIdQueryResponse, GetSceneByIdPathParams, GetSceneById404, GetSceneById500 } from '../../models/GetSceneById.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getSceneByIdSuspenseQueryKey = ({ sceneId }: { sceneId: GetSceneByIdPathParams['scene-id'] }) =>
  ['v1', { url: '/scenes/:scene-id', params: { sceneId: sceneId } }] as const

export type GetSceneByIdSuspenseQueryKey = ReturnType<typeof getSceneByIdSuspenseQueryKey>

/**
 * @description Retrieve details of a specific scene by its ID
 * @summary Get details of a scene
 * {@link /scenes/:scene-id}
 */
export async function getSceneByIdSuspenseHook(
  { sceneId }: { sceneId: GetSceneByIdPathParams['scene-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetSceneByIdQueryResponse, ResponseErrorConfig<GetSceneById404 | GetSceneById500>, unknown>({
    method: 'GET',
    url: `/scenes/${sceneId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getSceneByIdSuspenseQueryOptionsHook({ sceneId }: { sceneId: GetSceneByIdPathParams['scene-id'] }, config: Partial<RequestConfig> = {}) {
  const queryKey = getSceneByIdSuspenseQueryKey({ sceneId })
  return queryOptions<GetSceneByIdQueryResponse, ResponseErrorConfig<GetSceneById404 | GetSceneById500>, GetSceneByIdQueryResponse, typeof queryKey>({
    enabled: !!sceneId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getSceneByIdSuspenseHook({ sceneId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific scene by its ID
 * @summary Get details of a scene
 * {@link /scenes/:scene-id}
 */
export function useGetSceneByIdSuspenseHook<
  TData = GetSceneByIdQueryResponse,
  TQueryData = GetSceneByIdQueryResponse,
  TQueryKey extends QueryKey = GetSceneByIdSuspenseQueryKey,
>(
  { sceneId }: { sceneId: GetSceneByIdPathParams['scene-id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetSceneByIdQueryResponse, ResponseErrorConfig<GetSceneById404 | GetSceneById500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getSceneByIdSuspenseQueryKey({ sceneId })

  const query = useSuspenseQuery({
    ...(getSceneByIdSuspenseQueryOptionsHook({ sceneId }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetSceneById404 | GetSceneById500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}