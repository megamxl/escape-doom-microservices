import client from '@kubb/plugin-client/clients/axios'
import type { AddERTagMutationResponse, AddERTagPathParams } from '../../models/AddERTag.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const addERTagMutationKey = () => [{ url: '/tag/{session_id}/{tag_name}' }] as const

export type AddERTagMutationKey = ReturnType<typeof addERTagMutationKey>

/**
 * @description Add a tag to an escape-room instance
 * @summary Add a tag to an escape-room instance
 * {@link /tag/:session_id/:tag_name}
 */
export async function addERTagHook(
  { session_id, tag_name }: { session_id: AddERTagPathParams['session_id']; tag_name: AddERTagPathParams['tag_name'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<AddERTagMutationResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'PUT',
    url: `/tag/${session_id}/${tag_name}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Add a tag to an escape-room instance
 * @summary Add a tag to an escape-room instance
 * {@link /tag/:session_id/:tag_name}
 */
export function useAddERTagHook(
  options: {
    mutation?: UseMutationOptions<
      AddERTagMutationResponse,
      ResponseErrorConfig<Error>,
      { session_id: AddERTagPathParams['session_id']; tag_name: AddERTagPathParams['tag_name'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? addERTagMutationKey()

  return useMutation<
    AddERTagMutationResponse,
    ResponseErrorConfig<Error>,
    { session_id: AddERTagPathParams['session_id']; tag_name: AddERTagPathParams['tag_name'] }
  >({
    mutationFn: async ({ session_id, tag_name }) => {
      return addERTagHook({ session_id, tag_name }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}