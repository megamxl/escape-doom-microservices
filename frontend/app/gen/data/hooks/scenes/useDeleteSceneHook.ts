import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { DeleteSceneMutationResponse, DeleteScenePathParams, DeleteScene404, DeleteScene500 } from '../../models/DeleteScene.ts'
import { useMutation } from '@tanstack/react-query'

export const deleteSceneMutationKey = () => [{ url: '/scenes/{escape-room-scene-id}' }] as const

export type DeleteSceneMutationKey = ReturnType<typeof deleteSceneMutationKey>

/**
 * @description Delete a specific Scene by its ID
 * @summary Delete a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export async function deleteSceneHook(
  { escapeRoomSceneId }: { escapeRoomSceneId: DeleteScenePathParams['escape-room-scene-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteSceneMutationResponse, ResponseErrorConfig<DeleteScene404 | DeleteScene500>, unknown>({
    method: 'DELETE',
    url: `/scenes/${escapeRoomSceneId}`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1',
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Delete a specific Scene by its ID
 * @summary Delete a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export function useDeleteSceneHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteSceneMutationResponse,
      ResponseErrorConfig<DeleteScene404 | DeleteScene500>,
      { escapeRoomSceneId: DeleteScenePathParams['escape-room-scene-id'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteSceneMutationKey()

  return useMutation<
    DeleteSceneMutationResponse,
    ResponseErrorConfig<DeleteScene404 | DeleteScene500>,
    { escapeRoomSceneId: DeleteScenePathParams['escape-room-scene-id'] }
  >({
    mutationFn: async ({ escapeRoomSceneId }) => {
      return deleteSceneHook({ escapeRoomSceneId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}