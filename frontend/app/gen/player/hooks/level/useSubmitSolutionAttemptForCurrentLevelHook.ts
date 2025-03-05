import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { UseMutationOptions } from '@tanstack/react-query'
import type {
  SubmitSolutionAttemptForCurrentLevelMutationRequest,
  SubmitSolutionAttemptForCurrentLevelMutationResponse,
  SubmitSolutionAttemptForCurrentLevelPathParams,
} from '../../models/SubmitSolutionAttemptForCurrentLevel.ts'
import { useMutation } from '@tanstack/react-query'

export const submitSolutionAttemptForCurrentLevelMutationKey = () => [{ url: '/level/{player_session_id}/submit' }] as const

export type SubmitSolutionAttemptForCurrentLevelMutationKey = ReturnType<typeof submitSolutionAttemptForCurrentLevelMutationKey>

/**
 * @description Submit a possible solution for the current level of the escape-room instance
 * @summary Submit a possible solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/submit}
 */
export async function submitSolutionAttemptForCurrentLevelHook(
  { player_session_id }: { player_session_id: SubmitSolutionAttemptForCurrentLevelPathParams['player_session_id'] },
  data?: SubmitSolutionAttemptForCurrentLevelMutationRequest,
  config: Partial<RequestConfig<SubmitSolutionAttemptForCurrentLevelMutationRequest>> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<
    SubmitSolutionAttemptForCurrentLevelMutationResponse,
    ResponseErrorConfig<Error>,
    SubmitSolutionAttemptForCurrentLevelMutationRequest
  >({ method: 'POST', url: `/level/${player_session_id}/submit`, baseURL: '${process.env.NEXT_PUBLIC_GW_URI}/player-api/v1', data, ...requestConfig })
  return res.data
}

/**
 * @description Submit a possible solution for the current level of the escape-room instance
 * @summary Submit a possible solution for the current level of the escape-room instance
 * {@link /level/:player_session_id/submit}
 */
export function useSubmitSolutionAttemptForCurrentLevelHook(
  options: {
    mutation?: UseMutationOptions<
      SubmitSolutionAttemptForCurrentLevelMutationResponse,
      ResponseErrorConfig<Error>,
      { player_session_id: SubmitSolutionAttemptForCurrentLevelPathParams['player_session_id']; data?: SubmitSolutionAttemptForCurrentLevelMutationRequest }
    >
    client?: Partial<RequestConfig<SubmitSolutionAttemptForCurrentLevelMutationRequest>>
  } = {},
) {
  const { mutation: mutationOptions, client: config = {} } = options ?? {}
  const mutationKey = mutationOptions?.mutationKey ?? submitSolutionAttemptForCurrentLevelMutationKey()

  return useMutation<
    SubmitSolutionAttemptForCurrentLevelMutationResponse,
    ResponseErrorConfig<Error>,
    { player_session_id: SubmitSolutionAttemptForCurrentLevelPathParams['player_session_id']; data?: SubmitSolutionAttemptForCurrentLevelMutationRequest }
  >({
    mutationFn: async ({ player_session_id, data }) => {
      return submitSolutionAttemptForCurrentLevelHook({ player_session_id }, data, config)
    },
    mutationKey,
    ...mutationOptions,
  })
}