import client from '@kubb/plugin-client/clients/axios'
import type {
  UpdateTemplateMutationRequest,
  UpdateTemplateMutationResponse,
  UpdateTemplatePathParams,
  UpdateTemplate400,
  UpdateTemplate404,
  UpdateTemplate500,
} from '../../models/UpdateTemplate.ts'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import { useMutation } from '@tanstack/react-query'

export const updateTemplateMutationKey = () => [{ url: '/templates/{template-id}' }] as const

export type UpdateTemplateMutationKey = ReturnType<typeof updateTemplateMutationKey>

/**
 * @description Override the name, description, and levels of an existing Template
 * @summary Overrides an existing Template
 * {@link /templates/:template-id}
 */
export async function updateTemplateHook(
  { templateId }: { templateId: UpdateTemplatePathParams['template-id'] },
  data?: UpdateTemplateMutationRequest,
  config: Partial<RequestConfig<UpdateTemplateMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<
    UpdateTemplateMutationResponse,
    ResponseErrorConfig<UpdateTemplate400 | UpdateTemplate404 | UpdateTemplate500>,
    UpdateTemplateMutationRequest
  >({ method: 'PUT', url: `/templates/${templateId}`, baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`, data, ...requestConfig })
  return res.data
}

/**
 * @description Override the name, description, and levels of an existing Template
 * @summary Overrides an existing Template
 * {@link /templates/:template-id}
 */
export function useUpdateTemplateHook(
  options: {
    mutation?: UseMutationOptions<
      UpdateTemplateMutationResponse,
      ResponseErrorConfig<UpdateTemplate400 | UpdateTemplate404 | UpdateTemplate500>,
      { templateId: UpdateTemplatePathParams['template-id']; data?: UpdateTemplateMutationRequest }
    >
    client?: Partial<RequestConfig<UpdateTemplateMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? updateTemplateMutationKey()

  return useMutation<
    UpdateTemplateMutationResponse,
    ResponseErrorConfig<UpdateTemplate400 | UpdateTemplate404 | UpdateTemplate500>,
    { templateId: UpdateTemplatePathParams['template-id']; data?: UpdateTemplateMutationRequest }
  >({
    mutationFn: async ({ templateId, data }) => {
      return updateTemplateHook({ templateId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}