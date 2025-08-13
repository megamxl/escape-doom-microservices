import client from '@kubb/plugin-client/clients/axios'
import type {
  DeleteTemplateMutationResponse,
  DeleteTemplatePathParams,
  DeleteTemplate400,
  DeleteTemplate404,
  DeleteTemplate500,
} from '../../models/DeleteTemplate.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const deleteTemplateMutationKey = () => [{ url: '/templates/{template-id}' }] as const

export type DeleteTemplateMutationKey = ReturnType<typeof deleteTemplateMutationKey>

/**
 * @description Deletes an Template by its unique ID
 * @summary Deletes an Template
 * {@link /templates/:template-id}
 */
export async function deleteTemplateHook(
  { templateId }: { templateId: DeleteTemplatePathParams['template-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<DeleteTemplateMutationResponse, ResponseErrorConfig<DeleteTemplate400 | DeleteTemplate404 | DeleteTemplate500>, unknown>({
    method: 'DELETE',
    url: `/templates/${templateId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Deletes an Template by its unique ID
 * @summary Deletes an Template
 * {@link /templates/:template-id}
 */
export function useDeleteTemplateHook(
  options: {
    mutation?: UseMutationOptions<
      DeleteTemplateMutationResponse,
      ResponseErrorConfig<DeleteTemplate400 | DeleteTemplate404 | DeleteTemplate500>,
      { templateId: DeleteTemplatePathParams['template-id'] }
    >
    client?: Partial<RequestConfig>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? deleteTemplateMutationKey()

  return useMutation<
    DeleteTemplateMutationResponse,
    ResponseErrorConfig<DeleteTemplate400 | DeleteTemplate404 | DeleteTemplate500>,
    { templateId: DeleteTemplatePathParams['template-id'] }
  >({
    mutationFn: async ({ templateId }) => {
      return deleteTemplateHook({ templateId }, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}