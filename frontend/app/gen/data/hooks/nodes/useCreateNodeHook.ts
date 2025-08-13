import client from '@kubb/plugin-client/clients/axios'
import type { CreateNodeMutationRequest, CreateNodeMutationResponse } from '../../models/CreateNode.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const createNodeMutationKey = () => [{ url: '/nodes' }] as const

export type CreateNodeMutationKey = ReturnType<typeof createNodeMutationKey>

/**
 * @description Creates a new node for a specific level
 * @summary Creates node for level
 * {@link /nodes}
 */
export async function createNodeHook(
  data?: CreateNodeMutationRequest,
  config: Partial<RequestConfig<CreateNodeMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<CreateNodeMutationResponse, ResponseErrorConfig<Error>, CreateNodeMutationRequest>({
    method: 'POST',
    url: `/nodes`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Creates a new node for a specific level
 * @summary Creates node for level
 * {@link /nodes}
 */
export function useCreateNodeHook(
  options: {
    mutation?: UseMutationOptions<CreateNodeMutationResponse, ResponseErrorConfig<Error>, { data?: CreateNodeMutationRequest }>
    client?: Partial<RequestConfig<CreateNodeMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? createNodeMutationKey()

  return useMutation<CreateNodeMutationResponse, ResponseErrorConfig<Error>, { data?: CreateNodeMutationRequest }>({
    mutationFn: async ({ data }) => {
      return createNodeHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}