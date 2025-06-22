import client from '@kubb/plugin-client/clients/axios'
import type { DeleteNodeMutationResponse, DeleteNodePathParams, DeleteNode404, DeleteNode500 } from '../../models/DeleteNode.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const deleteNodeMutationKey = () => [{ url: '/nodes/{node-id}' }] as const

export type DeleteNodeMutationKey = ReturnType<typeof deleteNodeMutationKey>

/**
 * @description Delete a node by its ID
 * @summary Delete a node
 * {@link /nodes/:node-id}
 */
export async function deleteNodeHook(
  { nodeId }: { nodeId: DeleteNodePathParams['node-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteNodeMutationResponse, ResponseErrorConfig<DeleteNode404 | DeleteNode500>, unknown>({
    method: 'DELETE',
    url: `/nodes/${nodeId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Delete a node by its ID
 * @summary Delete a node
 * {@link /nodes/:node-id}
 */
export function useDeleteNodeHook(
  options: {
    mutation?: UseMutationOptions<DeleteNodeMutationResponse, ResponseErrorConfig<DeleteNode404 | DeleteNode500>, { nodeId: DeleteNodePathParams['node-id'] }>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteNodeMutationKey()

  return useMutation<DeleteNodeMutationResponse, ResponseErrorConfig<DeleteNode404 | DeleteNode500>, { nodeId: DeleteNodePathParams['node-id'] }>({
    mutationFn: async ({ nodeId }) => {
      return deleteNodeHook({ nodeId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}