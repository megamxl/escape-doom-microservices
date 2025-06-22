import client from '@kubb/plugin-client/clients/axios'
import type { DeleteLevelMutationResponse, DeleteLevelPathParams, DeleteLevel404, DeleteLevel500 } from '../../models/DeleteLevel.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const deleteLevelMutationKey = () => [{ url: '/levels/{level-id}' }] as const

export type DeleteLevelMutationKey = ReturnType<typeof deleteLevelMutationKey>

/**
 * @description Delete a Level by its ID
 * @summary Delete a level
 * {@link /levels/:level-id}
 */
export async function deleteLevelHook(
  { levelId }: { levelId: DeleteLevelPathParams['level-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteLevelMutationResponse, ResponseErrorConfig<DeleteLevel404 | DeleteLevel500>, unknown>({
    method: 'DELETE',
    url: `/levels/${levelId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Delete a Level by its ID
 * @summary Delete a level
 * {@link /levels/:level-id}
 */
export function useDeleteLevelHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteLevelMutationResponse,
      ResponseErrorConfig<DeleteLevel404 | DeleteLevel500>,
      { levelId: DeleteLevelPathParams['level-id'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteLevelMutationKey()

  return useMutation<DeleteLevelMutationResponse, ResponseErrorConfig<DeleteLevel404 | DeleteLevel500>, { levelId: DeleteLevelPathParams['level-id'] }>({
    mutationFn: async ({ levelId }) => {
      return deleteLevelHook({ levelId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}