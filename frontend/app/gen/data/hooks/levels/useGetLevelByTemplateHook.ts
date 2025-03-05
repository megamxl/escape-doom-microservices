import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetLevelByTemplateQueryResponse, GetLevelByTemplatePathParams } from '../../models/GetLevelByTemplate.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getLevelByTemplateQueryKey = ({ escapeRoomTemplateId }: { escapeRoomTemplateId: GetLevelByTemplatePathParams['escape-room-template-id'] }) =>
  ['v1', { url: '/levels/:escape-room-template-id', params: { escapeRoomTemplateId: escapeRoomTemplateId } }] as const

export type GetLevelByTemplateQueryKey = ReturnType<typeof getLevelByTemplateQueryKey>

/**
 * @description Retrieve all levels associated with a specific template
 * @summary Retrieve levels
 * {@link /levels/:escape-room-template-id}
 */
export async function getLevelByTemplateHook(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetLevelByTemplatePathParams['escape-room-template-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetLevelByTemplateQueryResponse, ResponseErrorConfig<Error>, unknown>({
    method: 'GET',
    url: `/levels/${escapeRoomTemplateId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getLevelByTemplateQueryOptionsHook(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetLevelByTemplatePathParams['escape-room-template-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelByTemplateQueryKey({ escapeRoomTemplateId })
  return queryOptions<GetLevelByTemplateQueryResponse, ResponseErrorConfig<Error>, GetLevelByTemplateQueryResponse, typeof queryKey>({
    enabled: !!escapeRoomTemplateId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelByTemplateHook({ escapeRoomTemplateId }, config)
    },
  })
}

/**
 * @description Retrieve all levels associated with a specific template
 * @summary Retrieve levels
 * {@link /levels/:escape-room-template-id}
 */
export function useGetLevelByTemplateHook<
  TData = GetLevelByTemplateQueryResponse,
  TQueryData = GetLevelByTemplateQueryResponse,
  TQueryKey extends QueryKey = GetLevelByTemplateQueryKey,
>(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetLevelByTemplatePathParams['escape-room-template-id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetLevelByTemplateQueryResponse, ResponseErrorConfig<Error>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelByTemplateQueryKey({ escapeRoomTemplateId })

  const query = useQuery({
    ...(getLevelByTemplateQueryOptionsHook({ escapeRoomTemplateId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}