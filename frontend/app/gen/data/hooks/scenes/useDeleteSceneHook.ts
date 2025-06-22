import client from '@kubb/plugin-client/clients/axios'
import type { DeleteSceneMutationResponse, DeleteScenePathParams, DeleteScene404, DeleteScene500 } from '../../models/DeleteScene.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const deleteSceneMutationKey = () => [{ url: '/scenes/{scene-id}' }] as const

export type DeleteSceneMutationKey = ReturnType<typeof deleteSceneMutationKey>

/**
 * @description Delete a specific Scene by its ID
 * @summary Delete a scene
 * {@link /scenes/:scene-id}
 */
export async function deleteSceneHook(
  { sceneId }: { sceneId: DeleteScenePathParams['scene-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteSceneMutationResponse, ResponseErrorConfig<DeleteScene404 | DeleteScene500>, unknown>({
    method: 'DELETE',
    url: `/scenes/${sceneId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Delete a specific Scene by its ID
 * @summary Delete a scene
 * {@link /scenes/:scene-id}
 */
export function useDeleteSceneHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteSceneMutationResponse,
      ResponseErrorConfig<DeleteScene404 | DeleteScene500>,
      { sceneId: DeleteScenePathParams['scene-id'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteSceneMutationKey()

  return useMutation<DeleteSceneMutationResponse, ResponseErrorConfig<DeleteScene404 | DeleteScene500>, { sceneId: DeleteScenePathParams['scene-id'] }>({
    mutationFn: async ({ sceneId }) => {
      return deleteSceneHook({ sceneId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}