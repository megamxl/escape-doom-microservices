import client from '@kubb/plugin-client/clients/axios'
import type { HandlePlayerJoinMutationRequest, HandlePlayerJoinMutationResponse, HandlePlayerJoin500 } from '../../models/HandlePlayerJoin.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const handlePlayerJoinMutationKey = () => [{ url: '/join' }] as const

export type HandlePlayerJoinMutationKey = ReturnType<typeof handlePlayerJoinMutationKey>

/**
 * @description Join an escape-room instance
 * @summary Join an escape-room instance
 * {@link /join}
 */
export async function handlePlayerJoinHook(
  data?: HandlePlayerJoinMutationRequest,
  config: Partial<RequestConfig<HandlePlayerJoinMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<HandlePlayerJoinMutationResponse, ResponseErrorConfig<HandlePlayerJoin500>, HandlePlayerJoinMutationRequest>({
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
export function useHandlePlayerJoinHook(
  options: {
    mutation?: UseMutationOptions<HandlePlayerJoinMutationResponse, ResponseErrorConfig<HandlePlayerJoin500>, { data?: HandlePlayerJoinMutationRequest }>
    client?: Partial<RequestConfig<HandlePlayerJoinMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? handlePlayerJoinMutationKey()

  return useMutation<HandlePlayerJoinMutationResponse, ResponseErrorConfig<HandlePlayerJoin500>, { data?: HandlePlayerJoinMutationRequest }>({
    mutationFn: async ({ data }) => {
      return handlePlayerJoinHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}