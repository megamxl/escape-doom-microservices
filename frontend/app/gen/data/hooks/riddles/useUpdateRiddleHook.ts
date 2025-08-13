import client from '@kubb/plugin-client/clients/axios'
import type {
  UpdateRiddleMutationRequest,
  UpdateRiddleMutationResponse,
  UpdateRiddlePathParams,
  UpdateRiddle400,
  UpdateRiddle404,
  UpdateRiddle500,
} from '../../models/UpdateRiddle.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const updateRiddleMutationKey = () => [{ url: '/riddles/{riddle-id}' }] as const

export type UpdateRiddleMutationKey = ReturnType<typeof updateRiddleMutationKey>

/**
 * @description Override the details of a riddle
 * @summary Override a riddle
 * {@link /riddles/:riddle-id}
 */
export async function updateRiddleHook(
  { riddleId }: { riddleId: UpdateRiddlePathParams['riddle-id'] },
  data?: UpdateRiddleMutationRequest,
  config: Partial<RequestConfig<UpdateRiddleMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<
    UpdateRiddleMutationResponse,
    ResponseErrorConfig<UpdateRiddle400 | UpdateRiddle404 | UpdateRiddle500>,
    UpdateRiddleMutationRequest
  >({ method: 'PUT', url: `/riddles/${riddleId}`, baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`, data, ...requestConfig })
  return res.data
}

/**
 * @description Override the details of a riddle
 * @summary Override a riddle
 * {@link /riddles/:riddle-id}
 */
export function useUpdateRiddleHook(
  options: {
    mutation?: UseMutationOptions<
      UpdateRiddleMutationResponse,
      ResponseErrorConfig<UpdateRiddle400 | UpdateRiddle404 | UpdateRiddle500>,
      { riddleId: UpdateRiddlePathParams['riddle-id']; data?: UpdateRiddleMutationRequest }
    >
    client?: Partial<RequestConfig<UpdateRiddleMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? updateRiddleMutationKey()

  return useMutation<
    UpdateRiddleMutationResponse,
    ResponseErrorConfig<UpdateRiddle400 | UpdateRiddle404 | UpdateRiddle500>,
    { riddleId: UpdateRiddlePathParams['riddle-id']; data?: UpdateRiddleMutationRequest }
  >({
    mutationFn: async ({ riddleId, data }) => {
      return updateRiddleHook({ riddleId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}