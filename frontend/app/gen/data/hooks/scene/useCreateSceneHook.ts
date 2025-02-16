import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type { CreateSceneMutationRequest, CreateSceneMutationResponse, CreateScene400, CreateScene500 } from '../../models/CreateScene.ts'
import { useMutation } from '@tanstack/react-query'

export const createSceneMutationKey = () => [{ url: '/scene' }] as const

export type CreateSceneMutationKey = ReturnType<typeof createSceneMutationKey>

/**
 * @description Create a Scene independently of any level
 * @summary Create a new scene
 * {@link /scene}
 */
export async function createSceneHook(
  data?: CreateSceneMutationRequest,
  config: Partial<RequestConfig<CreateSceneMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<CreateSceneMutationResponse, ResponseErrorConfig<CreateScene400 | CreateScene500>, CreateSceneMutationRequest>({
    method: 'POST',
    url: `/scene`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Create a Scene independently of any level
 * @summary Create a new scene
 * {@link /scene}
 */
export function useCreateSceneHook(
  options: {
    mutation?: UseMutationOptions<CreateSceneMutationResponse, ResponseErrorConfig<CreateScene400 | CreateScene500>, { data?: CreateSceneMutationRequest }>
    client?: Partial<RequestConfig<CreateSceneMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? createSceneMutationKey()

  return useMutation<CreateSceneMutationResponse, ResponseErrorConfig<CreateScene400 | CreateScene500>, { data?: CreateSceneMutationRequest }>({
    mutationFn: async ({ data }) => {
      return createSceneHook(data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}