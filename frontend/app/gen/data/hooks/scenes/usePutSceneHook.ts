import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { PutSceneMutationRequest, PutSceneMutationResponse, PutScenePathParams, PutScene400, PutScene404, PutScene500 } from '../../models/PutScene.ts'
import { useMutation } from '@tanstack/react-query'

export const putSceneMutationKey = () => [{ url: '/scenes/{escape-room-scene-id}' }] as const

export type PutSceneMutationKey = ReturnType<typeof putSceneMutationKey>

/**
 * @description Update the details of a specific Scene
 * @summary Update a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export async function putSceneHook(
  { escapeRoomSceneId }: { escapeRoomSceneId: PutScenePathParams['escape-room-scene-id'] },
  data?: PutSceneMutationRequest,
  config: Partial<RequestConfig<PutSceneMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<PutSceneMutationResponse, ResponseErrorConfig<PutScene400 | PutScene404 | PutScene500>, PutSceneMutationRequest>({
    method: 'PUT',
    url: `/scenes/${escapeRoomSceneId}`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1',
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Update the details of a specific Scene
 * @summary Update a scene
 * {@link /scenes/:escape-room-scene-id}
 */
export function usePutSceneHook(
  options: {
    mutation?: UseMutationOptions<
      PutSceneMutationResponse,
      ResponseErrorConfig<PutScene400 | PutScene404 | PutScene500>,
      { escapeRoomSceneId: PutScenePathParams['escape-room-scene-id']; data?: PutSceneMutationRequest }
    >
    client?: Partial<RequestConfig<PutSceneMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? putSceneMutationKey()

  return useMutation<
    PutSceneMutationResponse,
    ResponseErrorConfig<PutScene400 | PutScene404 | PutScene500>,
    { escapeRoomSceneId: PutScenePathParams['escape-room-scene-id']; data?: PutSceneMutationRequest }
  >({
    mutationFn: async ({ escapeRoomSceneId, data }) => {
      return putSceneHook({ escapeRoomSceneId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}