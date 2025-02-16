import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { DeleteRiddleMutationResponse, DeleteRiddlePathParams, DeleteRiddle404, DeleteRiddle500 } from '../../models/DeleteRiddle.ts'
import { useMutation } from '@tanstack/react-query'

export const deleteRiddleMutationKey = () => [{ url: '/riddles/{escape-room-riddle-id}' }] as const

export type DeleteRiddleMutationKey = ReturnType<typeof deleteRiddleMutationKey>

/**
 * @description Delete a riddle that is not linked to any level
 * @summary Delete a riddle
 * {@link /riddles/:escape-room-riddle-id}
 */
export async function deleteRiddleHook(
  { escapeRoomRiddleId }: { escapeRoomRiddleId: DeleteRiddlePathParams['escape-room-riddle-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteRiddleMutationResponse, ResponseErrorConfig<DeleteRiddle404 | DeleteRiddle500>, unknown>({
    method: 'DELETE',
    url: `/riddles/${escapeRoomRiddleId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Delete a riddle that is not linked to any level
 * @summary Delete a riddle
 * {@link /riddles/:escape-room-riddle-id}
 */
export function useDeleteRiddleHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteRiddleMutationResponse,
      ResponseErrorConfig<DeleteRiddle404 | DeleteRiddle500>,
      { escapeRoomRiddleId: DeleteRiddlePathParams['escape-room-riddle-id'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteRiddleMutationKey()

  return useMutation<
    DeleteRiddleMutationResponse,
    ResponseErrorConfig<DeleteRiddle404 | DeleteRiddle500>,
    { escapeRoomRiddleId: DeleteRiddlePathParams['escape-room-riddle-id'] }
  >({
    mutationFn: async ({ escapeRoomRiddleId }) => {
      return deleteRiddleHook({ escapeRoomRiddleId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}