import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetLevelByTemplateQueryResponse, GetLevelByTemplatePathParams } from '../../models/GetLevelByTemplate.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getLevelByTemplateSuspenseQueryKey = ({
  escapeRoomTemplateId,
}: {
  escapeRoomTemplateId: GetLevelByTemplatePathParams['escape-room-template-id']
}) => ['v1', { url: '/levels/:escape-room-template-id', params: { escapeRoomTemplateId: escapeRoomTemplateId } }] as const

export type GetLevelByTemplateSuspenseQueryKey = ReturnType<typeof getLevelByTemplateSuspenseQueryKey>

/**
 * @description Retrieve all levels associated with a specific template
 * @summary Retrieve levels
 * {@link /levels/:escape-room-template-id}
 */
export async function getLevelByTemplateSuspenseHook(
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

export function getLevelByTemplateSuspenseQueryOptionsHook(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetLevelByTemplatePathParams['escape-room-template-id'] },
  config: Partial<RequestConfig> = {},
) {
  const queryKey = getLevelByTemplateSuspenseQueryKey({ escapeRoomTemplateId })
  return queryOptions<GetLevelByTemplateQueryResponse, ResponseErrorConfig<Error>, GetLevelByTemplateQueryResponse, typeof queryKey>({
    enabled: !!escapeRoomTemplateId,
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getLevelByTemplateSuspenseHook({ escapeRoomTemplateId }, config)
    },
  })
}

/**
 * @description Retrieve all levels associated with a specific template
 * @summary Retrieve levels
 * {@link /levels/:escape-room-template-id}
 */
export function useGetLevelByTemplateSuspenseHook<
  TData = GetLevelByTemplateQueryResponse,
  TQueryData = GetLevelByTemplateQueryResponse,
  TQueryKey extends QueryKey = GetLevelByTemplateSuspenseQueryKey,
>(
  { escapeRoomTemplateId }: { escapeRoomTemplateId: GetLevelByTemplatePathParams['escape-room-template-id'] },
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetLevelByTemplateQueryResponse, ResponseErrorConfig<Error>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getLevelByTemplateSuspenseQueryKey({ escapeRoomTemplateId })

  const query = useSuspenseQuery({
    ...(getLevelByTemplateSuspenseQueryOptionsHook({ escapeRoomTemplateId }, config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<Error>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}