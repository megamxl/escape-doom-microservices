import client from '@kubb/plugin-client/clients/axios'
import type { CreateRiddleMutationRequest, CreateRiddleMutationResponse, CreateRiddle400, CreateRiddle500 } from '../../models/CreateRiddle.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const createRiddleMutationKey = () => [{ url: '/riddles' }] as const

export type CreateRiddleMutationKey = ReturnType<typeof createRiddleMutationKey>

/**
 * @description Create a riddle without linking it to a specific level
 * @summary Create a new riddle
 * {@link /riddles}
 */
export async function createRiddleHook(
  data?: CreateRiddleMutationRequest,
  config: Partial<RequestConfig<CreateRiddleMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<CreateRiddleMutationResponse, ResponseErrorConfig<CreateRiddle400 | CreateRiddle500>, CreateRiddleMutationRequest>({
    method: 'POST',
    url: `/riddles`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Create a riddle without linking it to a specific level
 * @summary Create a new riddle
 * {@link /riddles}
 */
export function useCreateRiddleHook(
  options: {
    mutation?: UseMutationOptions<CreateRiddleMutationResponse, ResponseErrorConfig<CreateRiddle400 | CreateRiddle500>, { data?: CreateRiddleMutationRequest }>
    client?: Partial<RequestConfig<CreateRiddleMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? createRiddleMutationKey()

  return useMutation<CreateRiddleMutationResponse, ResponseErrorConfig<CreateRiddle400 | CreateRiddle500>, { data?: CreateRiddleMutationRequest }>({
    mutationFn: async ({ data }) => {
      return createRiddleHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}