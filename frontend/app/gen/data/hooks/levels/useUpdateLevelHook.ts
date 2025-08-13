import client from '@kubb/plugin-client/clients/axios'
import type {
  UpdateLevelMutationRequest,
  UpdateLevelMutationResponse,
  UpdateLevelPathParams,
  UpdateLevel400,
  UpdateLevel404,
  UpdateLevel500,
} from '../../models/UpdateLevel.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const updateLevelMutationKey = () => [{ url: '/levels/{level-id}' }] as const

export type UpdateLevelMutationKey = ReturnType<typeof updateLevelMutationKey>

/**
 * @description Override the details of a Level
 * @summary Override a level
 * {@link /levels/:level-id}
 */
export async function updateLevelHook(
  { levelId }: { levelId: UpdateLevelPathParams['level-id'] },
  data?: UpdateLevelMutationRequest,
  config: Partial<RequestConfig<UpdateLevelMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<UpdateLevelMutationResponse, ResponseErrorConfig<UpdateLevel400 | UpdateLevel404 | UpdateLevel500>, UpdateLevelMutationRequest>({
    method: 'PUT',
    url: `/levels/${levelId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Override the details of a Level
 * @summary Override a level
 * {@link /levels/:level-id}
 */
export function useUpdateLevelHook(
  options: {
    mutation?: UseMutationOptions<
      UpdateLevelMutationResponse,
      ResponseErrorConfig<UpdateLevel400 | UpdateLevel404 | UpdateLevel500>,
      { levelId: UpdateLevelPathParams['level-id']; data?: UpdateLevelMutationRequest }
    >
    client?: Partial<RequestConfig<UpdateLevelMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? updateLevelMutationKey()

  return useMutation<
    UpdateLevelMutationResponse,
    ResponseErrorConfig<UpdateLevel400 | UpdateLevel404 | UpdateLevel500>,
    { levelId: UpdateLevelPathParams['level-id']; data?: UpdateLevelMutationRequest }
  >({
    mutationFn: async ({ levelId, data }) => {
      return updateLevelHook({ levelId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}