import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type {
  PutTemplateMutationRequest,
  PutTemplateMutationResponse,
  PutTemplatePathParams,
  PutTemplate400,
  PutTemplate404,
  PutTemplate500,
} from '../../models/PutTemplate.ts'
import { useMutation } from '@tanstack/react-query'

export const putTemplateMutationKey = () => [{ url: '/template/override/{escape-room-template-id}' }] as const

export type PutTemplateMutationKey = ReturnType<typeof putTemplateMutationKey>

/**
 * @description Override the name, description, and levels of an existing EscapeRoomTemplate
 * @summary Overrides an existing EscapeRoomTemplate
 * {@link /template/override/:escape-room-template-id}
 */
export async function putTemplateHook(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: PutTemplatePathParams['escape-room-template-id'] },
  data?: PutTemplateMutationRequest,
  config: Partial<RequestConfig<PutTemplateMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<PutTemplateMutationResponse, ResponseErrorConfig<PutTemplate400 | PutTemplate404 | PutTemplate500>, PutTemplateMutationRequest>({
    method: 'PUT',
    url: `/template/override/${escapeRoomTemplateId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Override the name, description, and levels of an existing EscapeRoomTemplate
 * @summary Overrides an existing EscapeRoomTemplate
 * {@link /template/override/:escape-room-template-id}
 */
export function usePutTemplateHook(
  options: {
    mutation?: UseMutationOptions<
      PutTemplateMutationResponse,
      ResponseErrorConfig<PutTemplate400 | PutTemplate404 | PutTemplate500>,
      { escapeRoomTemplateId: PutTemplatePathParams['escape-room-template-id']; data?: PutTemplateMutationRequest }
    >
    client?: Partial<RequestConfig<PutTemplateMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? putTemplateMutationKey()

  return useMutation<
    PutTemplateMutationResponse,
    ResponseErrorConfig<PutTemplate400 | PutTemplate404 | PutTemplate500>,
    { escapeRoomTemplateId: PutTemplatePathParams['escape-room-template-id']; data?: PutTemplateMutationRequest }
  >({
    mutationFn: async ({ escapeRoomTemplateId, data }) => {
      return putTemplateHook({ escapeRoomTemplateId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}