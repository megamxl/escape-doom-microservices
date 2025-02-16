import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type {
  DeleteTemplateMutationResponse,
  DeleteTemplatePathParams,
  DeleteTemplate400,
  DeleteTemplate404,
  DeleteTemplate500,
} from '../../models/DeleteTemplate.ts'
import { useMutation } from '@tanstack/react-query'

export const deleteTemplateMutationKey = () => [{ url: '/template/delete/{escape-room-template-id}' }] as const

export type DeleteTemplateMutationKey = ReturnType<typeof deleteTemplateMutationKey>

/**
 * @description Deletes an EscapeRoomTemplate by its unique ID
 * @summary Deletes an EscapeRoomTemplate
 * {@link /template/delete/:escape-room-template-id}
 */
export async function deleteTemplateHook(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: DeleteTemplatePathParams['escape-room-template-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteTemplateMutationResponse, ResponseErrorConfig<DeleteTemplate400 | DeleteTemplate404 | DeleteTemplate500>, unknown>({
    method: 'DELETE',
    url: `/template/delete/${escapeRoomTemplateId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Deletes an EscapeRoomTemplate by its unique ID
 * @summary Deletes an EscapeRoomTemplate
 * {@link /template/delete/:escape-room-template-id}
 */
export function useDeleteTemplateHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteTemplateMutationResponse,
      ResponseErrorConfig<DeleteTemplate400 | DeleteTemplate404 | DeleteTemplate500>,
      { escapeRoomTemplateId: DeleteTemplatePathParams['escape-room-template-id'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteTemplateMutationKey()

  return useMutation<
    DeleteTemplateMutationResponse,
    ResponseErrorConfig<DeleteTemplate400 | DeleteTemplate404 | DeleteTemplate500>,
    { escapeRoomTemplateId: DeleteTemplatePathParams['escape-room-template-id'] }
  >({
    mutationFn: async ({ escapeRoomTemplateId }) => {
      return deleteTemplateHook({ escapeRoomTemplateId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}