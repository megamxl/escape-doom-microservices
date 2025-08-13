import client from '@kubb/plugin-client/clients/axios'
import type {
  UpdateSceneMutationRequest,
  UpdateSceneMutationResponse,
  UpdateScenePathParams,
  UpdateScene400,
  UpdateScene404,
  UpdateScene500,
} from '../../models/UpdateScene.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const updateSceneMutationKey = () => [{ url: '/scenes/{scene-id}' }] as const

export type UpdateSceneMutationKey = ReturnType<typeof updateSceneMutationKey>

/**
 * @description Update the details of a specific Scene
 * @summary Update a scene
 * {@link /scenes/:scene-id}
 */
export async function updateSceneHook(
  { sceneId }: { sceneId: UpdateScenePathParams['scene-id'] },
  data?: UpdateSceneMutationRequest,
  config: Partial<RequestConfig<UpdateSceneMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<UpdateSceneMutationResponse, ResponseErrorConfig<UpdateScene400 | UpdateScene404 | UpdateScene500>, UpdateSceneMutationRequest>({
    method: 'PUT',
    url: `/scenes/${sceneId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Update the details of a specific Scene
 * @summary Update a scene
 * {@link /scenes/:scene-id}
 */
export function useUpdateSceneHook(
  options: {
    mutation?: UseMutationOptions<
      UpdateSceneMutationResponse,
      ResponseErrorConfig<UpdateScene400 | UpdateScene404 | UpdateScene500>,
      { sceneId: UpdateScenePathParams['scene-id']; data?: UpdateSceneMutationRequest }
    >
    client?: Partial<RequestConfig<UpdateSceneMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? updateSceneMutationKey()

  return useMutation<
    UpdateSceneMutationResponse,
    ResponseErrorConfig<UpdateScene400 | UpdateScene404 | UpdateScene500>,
    { sceneId: UpdateScenePathParams['scene-id']; data?: UpdateSceneMutationRequest }
  >({
    mutationFn: async ({ sceneId, data }) => {
      return updateSceneHook({ sceneId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}