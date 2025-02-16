import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { CreateLevelMutationRequest, CreateLevelMutationResponse, CreateLevel400, CreateLevel500 } from '../../models/CreateLevel.ts'
import { useMutation } from '@tanstack/react-query'

export const createLevelMutationKey = () => [{ url: '/level' }] as const

export type CreateLevelMutationKey = ReturnType<typeof createLevelMutationKey>

/**
 * @description Create an EscapeRoomLevel independently of any template
 * @summary Create a new level
 * {@link /level}
 */
export async function createLevelHook(
  data?: CreateLevelMutationRequest,
  config: Partial<RequestConfig<CreateLevelMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<CreateLevelMutationResponse, ResponseErrorConfig<CreateLevel400 | CreateLevel500>, CreateLevelMutationRequest>({
    method: 'POST',
    url: `/level`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Create an EscapeRoomLevel independently of any template
 * @summary Create a new level
 * {@link /level}
 */
export function useCreateLevelHook(
  options: {
    mutation?: UseMutationOptions<CreateLevelMutationResponse, ResponseErrorConfig<CreateLevel400 | CreateLevel500>, { data?: CreateLevelMutationRequest }>
    client?: Partial<RequestConfig<CreateLevelMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? createLevelMutationKey()

  return useMutation<CreateLevelMutationResponse, ResponseErrorConfig<CreateLevel400 | CreateLevel500>, { data?: CreateLevelMutationRequest }>({
    mutationFn: async ({ data }) => {
      return createLevelHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}