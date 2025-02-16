import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { CreateTemplateMutationRequest, CreateTemplateMutationResponse, CreateTemplate400, CreateTemplate500 } from '../../models/CreateTemplate.ts'
import { useMutation } from '@tanstack/react-query'

export const createTemplateMutationKey = () => [{ url: '/template/create' }] as const

export type CreateTemplateMutationKey = ReturnType<typeof createTemplateMutationKey>

/**
 * @description Creates a new Template for EscapeRoom
 * @summary Creates a new Template for Escape Doom Game
 * {@link /template/create}
 */
export async function createTemplateHook(
  data?: CreateTemplateMutationRequest,
  config: Partial<RequestConfig<CreateTemplateMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<CreateTemplateMutationResponse, ResponseErrorConfig<CreateTemplate400 | CreateTemplate500>, CreateTemplateMutationRequest>({
    method: 'POST',
    url: `/template/create`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Creates a new Template for EscapeRoom
 * @summary Creates a new Template for Escape Doom Game
 * {@link /template/create}
 */
export function useCreateTemplateHook(
  options: {
    mutation?: UseMutationOptions<
      CreateTemplateMutationResponse,
      ResponseErrorConfig<CreateTemplate400 | CreateTemplate500>,
      { data?: CreateTemplateMutationRequest }
    >
    client?: Partial<RequestConfig<CreateTemplateMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? createTemplateMutationKey()

  return useMutation<CreateTemplateMutationResponse, ResponseErrorConfig<CreateTemplate400 | CreateTemplate500>, { data?: CreateTemplateMutationRequest }>({
    mutationFn: async ({ data }) => {
      return createTemplateHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}