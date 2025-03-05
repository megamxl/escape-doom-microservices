import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { CreateERInstanceMutationRequest, CreateERInstanceMutationResponse } from '../../models/CreateERInstance.ts'
import { useMutation } from '@tanstack/react-query'

export const createERInstanceMutationKey = () => [{ url: '/create' }] as const

export type CreateERInstanceMutationKey = ReturnType<typeof createERInstanceMutationKey>

/**
 * @description Creates a new escape-room instance
 * @summary Create a new escape-room instance
 * {@link /create}
 */
export async function createERInstanceHook(
  data?: CreateERInstanceMutationRequest,
  config: Partial<RequestConfig<CreateERInstanceMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<CreateERInstanceMutationResponse, ResponseErrorConfig<Error>, CreateERInstanceMutationRequest>({
    method: 'POST',
    url: `/create`,
    baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/session-api/v1',
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Creates a new escape-room instance
 * @summary Create a new escape-room instance
 * {@link /create}
 */
export function useCreateERInstanceHook(
  options: {
    mutation?: UseMutationOptions<CreateERInstanceMutationResponse, ResponseErrorConfig<Error>, { data?: CreateERInstanceMutationRequest }>
    client?: Partial<RequestConfig<CreateERInstanceMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? createERInstanceMutationKey()

  return useMutation<CreateERInstanceMutationResponse, ResponseErrorConfig<Error>, { data?: CreateERInstanceMutationRequest }>({
    mutationFn: async ({ data }) => {
      return createERInstanceHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}