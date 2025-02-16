import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type {
  PutRiddleMutationRequest,
  PutRiddleMutationResponse,
  PutRiddlePathParams,
  PutRiddle400,
  PutRiddle404,
  PutRiddle500,
} from '../../models/PutRiddle.ts'
import { useMutation } from '@tanstack/react-query'

export const putRiddleMutationKey = () => [{ url: '/riddles/{escape-room-riddle-id}' }] as const

export type PutRiddleMutationKey = ReturnType<typeof putRiddleMutationKey>

/**
 * @description Override the details of a riddle
 * @summary Override a riddle
 * {@link /riddles/:escape-room-riddle-id}
 */
export async function putRiddleHook(
  { escapeRoomRiddleId }: { escapeRoomRiddleId: PutRiddlePathParams['escape-room-riddle-id'] },
  data?: PutRiddleMutationRequest,
  config: Partial<RequestConfig<PutRiddleMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<PutRiddleMutationResponse, ResponseErrorConfig<PutRiddle400 | PutRiddle404 | PutRiddle500>, PutRiddleMutationRequest>({
    method: 'PUT',
    url: `/riddles/${escapeRoomRiddleId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Override the details of a riddle
 * @summary Override a riddle
 * {@link /riddles/:escape-room-riddle-id}
 */
export function usePutRiddleHook(
  options: {
    mutation?: UseMutationOptions<
      PutRiddleMutationResponse,
      ResponseErrorConfig<PutRiddle400 | PutRiddle404 | PutRiddle500>,
      { escapeRoomRiddleId: PutRiddlePathParams['escape-room-riddle-id']; data?: PutRiddleMutationRequest }
    >
    client?: Partial<RequestConfig<PutRiddleMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? putRiddleMutationKey()

  return useMutation<
    PutRiddleMutationResponse,
    ResponseErrorConfig<PutRiddle400 | PutRiddle404 | PutRiddle500>,
    { escapeRoomRiddleId: PutRiddlePathParams['escape-room-riddle-id']; data?: PutRiddleMutationRequest }
  >({
    mutationFn: async ({ escapeRoomRiddleId, data }) => {
      return putRiddleHook({ escapeRoomRiddleId }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}