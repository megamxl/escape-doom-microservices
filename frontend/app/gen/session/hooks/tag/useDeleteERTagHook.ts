import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { DeleteERTagMutationResponse, DeleteERTagPathParams } from '../../models/DeleteERTag.ts'
import { useMutation } from '@tanstack/react-query'

export const deleteERTagMutationKey = () => [{ url: '/tag/{escape_room_session_id}/{tag_name}' }] as const

export type DeleteERTagMutationKey = ReturnType<typeof deleteERTagMutationKey>

/**
 * @description Remove a tag from an escape-room instance
 * @summary Remove a tag from an escape-room instance
 * {@link /tag/:escape_room_session_id/:tag_name}
 */
export async function deleteERTagHook(
  {
    escape_room_session_id,
    tag_name,
  }: { escape_room_session_id: DeleteERTagPathParams['escape_room_session_id']; tag_name: DeleteERTagPathParams['tag_name'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteERTagMutationResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'DELETE',
    url: `/tag/${escape_room_session_id}/${tag_name}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Remove a tag from an escape-room instance
 * @summary Remove a tag from an escape-room instance
 * {@link /tag/:escape_room_session_id/:tag_name}
 */
export function useDeleteERTagHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteERTagMutationResponse,
      ResponseErrorConfig<Error>,
      { escape_room_session_id: DeleteERTagPathParams['escape_room_session_id']; tag_name: DeleteERTagPathParams['tag_name'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteERTagMutationKey()

  return useMutation<
    DeleteERTagMutationResponse,
    ResponseErrorConfig<Error>,
    { escape_room_session_id: DeleteERTagPathParams['escape_room_session_id']; tag_name: DeleteERTagPathParams['tag_name'] }
  >({
    mutationFn: async ({ escape_room_session_id, tag_name }) => {
      return deleteERTagHook({ escape_room_session_id, tag_name }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}