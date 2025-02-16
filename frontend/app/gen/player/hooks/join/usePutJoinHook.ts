import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { PutJoinMutationRequest, PutJoinMutationResponse } from '../../models/PutJoin.ts'
import { useMutation } from '@tanstack/react-query'

export const putJoinMutationKey = () => [{ url: '/join' }] as const

export type PutJoinMutationKey = ReturnType<typeof putJoinMutationKey>

/**
 * @description Join an escape-room instance
 * @summary Join an escape-room instance
 * {@link /join}
 */
export async function putJoinHook(data?: PutJoinMutationRequest, config: Partial<RequestConfig<PutJoinMutationRequest>> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<PutJoinMutationResponse, ResponseErrorConfig<Error>, PutJoinMutationRequest>({
    method: 'PUT',
    url: `/join`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/player-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Join an escape-room instance
 * @summary Join an escape-room instance
 * {@link /join}
 */
export function usePutJoinHook(
  options: {
    mutation?: UseMutationOptions<PutJoinMutationResponse, ResponseErrorConfig<Error>, { data?: PutJoinMutationRequest }>
    client?: Partial<RequestConfig<PutJoinMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? putJoinMutationKey()

  return useMutation<PutJoinMutationResponse, ResponseErrorConfig<Error>, { data?: PutJoinMutationRequest }>({
    mutationFn: async ({ data }) => {
      return putJoinHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}