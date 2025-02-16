import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, QueryObserverOptions, UseQueryResult } from '@tanstack/react-query'
import type { GetTemplateQueryResponse, GetTemplatePathParams, GetTemplate404, GetTemplate500 } from '../../models/GetTemplate.ts'
import { queryOptions, useQuery } from '@tanstack/react-query'

export const getTemplateQueryKey = ({ escapeRoomTemplateId }: { escapeRoomTemplateId: GetTemplatePathParams['escape-room-template-id'] }) =>
  ['v1', { url: '/template/:escape-room-template-id', params: { escapeRoomTemplateId: escapeRoomTemplateId } }] as const

export type GetTemplateQueryKey = ReturnType<typeof getTemplateQueryKey>

/**
 * @description Retrieve details of a specific EscapeRoomTemplate using its unique ID
 * @summary Get a specific EscapeRoomTemplate by ID
 * {@link /template/:escape-room-template-id}
 */
export async function getTemplateHook(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetTemplatePathParams['escape-room-template-id'] },
  config: Partial<RequestConfig> & { client?: typeof client } = {},
) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetTemplateQueryResponse, ResponseErrorConfig<GetTemplate404 | GetTemplate500>, unknown>({
    method: 'GET',
    url: `/template/${escapeRoomTemplateId}`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getTemplateQueryOptionsHook(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetTemplatePathParams['escape-room-template-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getTemplateQueryKey({ escapeRoomTemplateId })
  return queryOptions<GetTemplateQueryResponse, ResponseErrorConfig<GetTemplate404 | GetTemplate500>, GetTemplateQueryResponse, typeof queryKey>({
    enabled: !!escapeRoomTemplateId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getTemplateHook({ escapeRoomTemplateId }, config)
    },
  })
}

/**
 * @description Retrieve details of a specific EscapeRoomTemplate using its unique ID
 * @summary Get a specific EscapeRoomTemplate by ID
 * {@link /template/:escape-room-template-id}
 */
export function useGetTemplateHook<TData = GetTemplateQueryResponse, TQueryData = GetTemplateQueryResponse, TQueryKey extends QueryKey = GetTemplateQueryKey>(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetTemplatePathParams['escape-room-template-id'] },
  options: {
    query?: Partial<QueryObserverOptions<GetTemplateQueryResponse, ResponseErrorConfig<GetTemplate404 | GetTemplate500>, TData, TQueryData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getTemplateQueryKey({ escapeRoomTemplateId })

  const query = useQuery({
    ...(getTemplateQueryOptionsHook({ escapeRoomTemplateId }, config) as unknown as QueryObserverOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<QueryObserverOptions, 'queryKey'>),
  }) as UseQueryResult<TData, ResponseErrorConfig<GetTemplate404 | GetTemplate500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}