import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type {
  PostLevelPlayerSessionIdSubmitMutationRequest,
  PostLevelPlayerSessionIdSubmitMutationResponse,
  PostLevelPlayerSessionIdSubmitPathParams,
} from '../../models/PostLevelPlayerSessionIdSubmit.ts'
import { useMutation } from '@tanstack/react-query'

export const postLevelPlayerSessionIdSubmitMutationKey = () => [{ url: '/level/{player_session_id}/submit' }] as const

export type PostLevelPlayerSessionIdSubmitMutationKey = ReturnType<typeof postLevelPlayerSessionIdSubmitMutationKey>

/**
 * @description Submit a possible solution for the current level of the escape-room instance
 * @summary Submit a possible solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/submit}
 */
export async function postLevelPlayerSessionIdSubmitHook(
  { player_session_id }: { player_session_id: PostLevelPlayerSessionIdSubmitPathParams['player_session_id'] },
  data?: PostLevelPlayerSessionIdSubmitMutationRequest,
  config: Partial<RequestConfig<PostLevelPlayerSessionIdSubmitMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<PostLevelPlayerSessionIdSubmitMutationResponse, ResponseErrorConfig<Error>, PostLevelPlayerSessionIdSubmitMutationRequest>({
    method: 'POST',
    url: `/level/${player_session_id}/submit`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/player-api/v1`,
    data,
    ...requestConfig,
  })
  return res.data
}

/**
 * @description Submit a possible solution for the current level of the escape-room instance
 * @summary Submit a possible solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/submit}
 */
export function usePostLevelPlayerSessionIdSubmitHook(
  options: {
    mutation?: UseMutationOptions<
      PostLevelPlayerSessionIdSubmitMutationResponse,
      ResponseErrorConfig<Error>,
      { player_session_id: PostLevelPlayerSessionIdSubmitPathParams['player_session_id']; data?: PostLevelPlayerSessionIdSubmitMutationRequest }
    >
    client?: Partial<RequestConfig<PostLevelPlayerSessionIdSubmitMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? postLevelPlayerSessionIdSubmitMutationKey()

  return useMutation<
    PostLevelPlayerSessionIdSubmitMutationResponse,
    ResponseErrorConfig<Error>,
    { player_session_id: PostLevelPlayerSessionIdSubmitPathParams['player_session_id']; data?: PostLevelPlayerSessionIdSubmitMutationRequest }
  >({
    mutationFn: async ({ player_session_id, data }) => {
      return postLevelPlayerSessionIdSubmitHook({ player_session_id }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}