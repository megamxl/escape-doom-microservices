import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { ToggleERInstanceStateMutationResponse, ToggleERInstanceStatePathParams } from '../../models/ToggleERInstanceState.ts'
import { useMutation } from '@tanstack/react-query'

export const toggleERInstanceStateMutationKey = () => [{ url: '/state/{escape_room_session_id}/{state}' }] as const

export type ToggleERInstanceStateMutationKey = ReturnType<typeof toggleERInstanceStateMutationKey>

/**
 * @description Starts or stops an escape-room instance
 * @summary Start or stop an escape-room instance
 * {@link /state/:escape_room_session_id/:state}
 */
export async function toggleERInstanceStateHook(
  {
    escape_room_session_id,
    state,
  }: { escape_room_session_id: ToggleERInstanceStatePathParams['escape_room_session_id']; state: ToggleERInstanceStatePathParams['state'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<ToggleERInstanceStateMutationResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'PUT',
    url: `/state/${escape_room_session_id}/${state}`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1',
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Starts or stops an escape-room instance
 * @summary Start or stop an escape-room instance
 * {@link /state/:escape_room_session_id/:state}
 */
export function useToggleERInstanceStateHook(
  options: {
    mutation?: UseMutationOptions<
      ToggleERInstanceStateMutationResponse,
      ResponseErrorConfig<Error>,
      { escape_room_session_id: ToggleERInstanceStatePathParams['escape_room_session_id']; state: ToggleERInstanceStatePathParams['state'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? toggleERInstanceStateMutationKey()

  return useMutation<
    ToggleERInstanceStateMutationResponse,
    ResponseErrorConfig<Error>,
    { escape_room_session_id: ToggleERInstanceStatePathParams['escape_room_session_id']; state: ToggleERInstanceStatePathParams['state'] }
  >({
    mutationFn: async ({ escape_room_session_id, state }) => {
      return toggleERInstanceStateHook({ escape_room_session_id, state }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}