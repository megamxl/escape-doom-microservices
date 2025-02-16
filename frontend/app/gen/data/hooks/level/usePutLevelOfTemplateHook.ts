import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type {
  PutLevelOfTemplateMutationRequest,
  PutLevelOfTemplateMutationResponse,
  PutLevelOfTemplatePathParams,
  PutLevelOfTemplate400,
  PutLevelOfTemplate404,
  PutLevelOfTemplate500,
} from '../../models/PutLevelOfTemplate.ts'
import { useMutation } from '@tanstack/react-query'

export const putLevelOfTemplateMutationKey = () => [{ url: '/level/overide/{escape-room-level-id}' }] as const

export type PutLevelOfTemplateMutationKey = ReturnType<typeof putLevelOfTemplateMutationKey>

/**
 * @description Override the details of a EscapeRoomLevel
 * @summary Override a level
 * {@link /level/overide/:escape-room-level-id}
 */
export async function putLevelOfTemplateHook(
  { escapeRoomLevelId }: { escapeRoomLevelId: PutLevelOfTemplatePathParams['escape-room-level-id'] },
  data?: PutLevelOfTemplateMutationRequest,
  config: Partial<RequestConfig<PutLevelOfTemplateMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<
    PutLevelOfTemplateMutationResponse,
    ResponseErrorConfig<PutLevelOfTemplate400 | PutLevelOfTemplate404 | PutLevelOfTemplate500>,
    PutLevelOfTemplateMutationRequest
  >({ method: 'PUT', url: `/level/overide/${escapeRoomLevelId}`, baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`, data, ...requestConfig })
  return res.data
}

/**
 * @description Override the details of a EscapeRoomLevel
 * @summary Override a level
 * {@link /level/overide/:escape-room-level-id}
 */
export function usePutLevelOfTemplateHook(
  options: {
    mutation?: UseMutationOptions<
      PutLevelOfTemplateMutationResponse,
      ResponseErrorConfig<PutLevelOfTemplate400 | PutLevelOfTemplate404 | PutLevelOfTemplate500>,
      { escapeRoomLevelId: PutLevelOfTemplatePathParams['escape-room-level-id']; data?: PutLevelOfTemplateMutationRequest }
    >
    client?: Partial<RequestConfig<PutLevelOfTemplateMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? putLevelOfTemplateMutationKey()

  return useMutation<
    PutLevelOfTemplateMutationResponse,
    ResponseErrorConfig<PutLevelOfTemplate400 | PutLevelOfTemplate404 | PutLevelOfTemplate500>,
    { escapeRoomLevelId: PutLevelOfTemplatePathParams['escape-room-level-id']; data?: PutLevelOfTemplateMutationRequest }
  >({
    mutationFn: async ({ escapeRoomLevelId, data }) => {
      return putLevelOfTemplateHook({ escapeRoomLevelId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}