import client from '@kubb/plugin-client/clients/axios'
import type {
  UpdateNodeMutationRequest,
  UpdateNodeMutationResponse,
  UpdateNodePathParams,
  UpdateNode400,
  UpdateNode404,
  UpdateNode500,
} from '../../models/UpdateNode.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const updateNodeMutationKey = () => [{ url: '/nodes/{node-id}' }] as const

export type UpdateNodeMutationKey = ReturnType<typeof updateNodeMutationKey>

/**
 * @description Override the details of a node
 * @summary Override a node
 * {@link /nodes/:node-id}
 */
export async function updateNodeHook(
  { nodeId }: { nodeId: UpdateNodePathParams['node-id'] },
  data?: UpdateNodeMutationRequest,
  config: Partial<RequestConfig<UpdateNodeMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<UpdateNodeMutationResponse, ResponseErrorConfig<UpdateNode400 | UpdateNode404 | UpdateNode500>, UpdateNodeMutationRequest>({
    method: 'PUT',
    url: `/nodes/${nodeId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Override the details of a node
 * @summary Override a node
 * {@link /nodes/:node-id}
 */
export function useUpdateNodeHook(
  options: {
    mutation?: UseMutationOptions<
      UpdateNodeMutationResponse,
      ResponseErrorConfig<UpdateNode400 | UpdateNode404 | UpdateNode500>,
      { nodeId: UpdateNodePathParams['node-id']; data?: UpdateNodeMutationRequest }
    >
    client?: Partial<RequestConfig<UpdateNodeMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? updateNodeMutationKey()

  return useMutation<
    UpdateNodeMutationResponse,
    ResponseErrorConfig<UpdateNode400 | UpdateNode404 | UpdateNode500>,
    { nodeId: UpdateNodePathParams['node-id']; data?: UpdateNodeMutationRequest }
  >({
    mutationFn: async ({ nodeId, data }) => {
      return updateNodeHook({ nodeId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}