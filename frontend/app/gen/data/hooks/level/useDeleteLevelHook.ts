import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { DeleteLevelMutationResponse, DeleteLevelPathParams, DeleteLevel404, DeleteLevel500 } from '../../models/DeleteLevel.ts'
import { useMutation } from '@tanstack/react-query'

export const deleteLevelMutationKey = () => [{ url: '/level/{escape-room-level-id}' }] as const

export type DeleteLevelMutationKey = ReturnType<typeof deleteLevelMutationKey>

/**
 * @description Delete a EscapeRoomLevel by its ID
 * @summary Delete a level
 * {@link /level/:escape-room-level-id}
 */
export async function deleteLevelHook(
  { escapeRoomLevelId }: { escapeRoomLevelId: DeleteLevelPathParams['escape-room-level-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteLevelMutationResponse, ResponseErrorConfig<DeleteLevel404 | DeleteLevel500>, unknown>({
    method: 'DELETE',
    url: `/level/${escapeRoomLevelId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Delete a EscapeRoomLevel by its ID
 * @summary Delete a level
 * {@link /level/:escape-room-level-id}
 */
export function useDeleteLevelHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteLevelMutationResponse,
      ResponseErrorConfig<DeleteLevel404 | DeleteLevel500>,
      { escapeRoomLevelId: DeleteLevelPathParams['escape-room-level-id'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteLevelMutationKey()

  return useMutation<
    DeleteLevelMutationResponse,
    ResponseErrorConfig<DeleteLevel404 | DeleteLevel500>,
    { escapeRoomLevelId: DeleteLevelPathParams['escape-room-level-id'] }
  >({
    mutationFn: async ({ escapeRoomLevelId }) => {
      return deleteLevelHook({ escapeRoomLevelId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}