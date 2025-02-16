import client from '@kubb/plugin-client/clients/axios'
import type { RequestConfig, ResponseErrorConfig } from '@kubb/plugin-client/clients/axios'
import type { QueryKey, UseSuspenseQueryOptions, UseSuspenseQueryResult } from '@tanstack/react-query'
import type { GetAllTemplatesQueryResponse, GetAllTemplates500 } from '../../models/GetAllTemplates.ts'
import { queryOptions, useSuspenseQuery } from '@tanstack/react-query'

export const getAllTemplatesSuspenseQueryKey = () => ['v1', { url: '/all-templates' }] as const

export type GetAllTemplatesSuspenseQueryKey = ReturnType<typeof getAllTemplatesSuspenseQueryKey>

/**
 * @description Retrieve a list of all existing EscapeRoomTemplates from a Lector
 * @summary Get all EscapeRoomTemplates
 * {@link /all-templates}
 */
export async function getAllTemplatesSuspenseHook(config: Partial<RequestConfig> & { client?: typeof client } = {}) {
  const { client: request = client, ...requestConfig } = config

  const res = await request<GetAllTemplatesQueryResponse, ResponseErrorConfig<GetAllTemplates500>, unknown>({
    method: 'GET',
    url: `/all-templates`,
    baseURL: `${process.env.NEXT_PUBLIC_GW_URI}/data-api/v1`,
    ...requestConfig,
  })
  return res.data
}

export function getAllTemplatesSuspenseQueryOptionsHook(config: Partial<RequestConfig> = {}) {
  const queryKey = getAllTemplatesSuspenseQueryKey()
  return queryOptions<GetAllTemplatesQueryResponse, ResponseErrorConfig<GetAllTemplates500>, GetAllTemplatesQueryResponse, typeof queryKey>({
    queryKey,
    queryFn: async ({ signal }) => {
      config.signal = signal
      return getAllTemplatesSuspenseHook(config)
    },
  })
}

/**
 * @description Retrieve a list of all existing EscapeRoomTemplates from a Lector
 * @summary Get all EscapeRoomTemplates
 * {@link /all-templates}
 */
export function useGetAllTemplatesSuspenseHook<
  TData = GetAllTemplatesQueryResponse,
  TQueryData = GetAllTemplatesQueryResponse,
  TQueryKey extends QueryKey = GetAllTemplatesSuspenseQueryKey,
>(
  options: {
    query?: Partial<UseSuspenseQueryOptions<GetAllTemplatesQueryResponse, ResponseErrorConfig<GetAllTemplates500>, TData, TQueryKey>>
    client?: Partial<RequestConfig>
  } = {},
) {
  const { query: queryOptions, client: config = {} } = options ?? {}
  const queryKey = queryOptions?.queryKey ?? getAllTemplatesSuspenseQueryKey()

  const query = useSuspenseQuery({
    ...(getAllTemplatesSuspenseQueryOptionsHook(config) as unknown as UseSuspenseQueryOptions),
    queryKey,
    ...(queryOptions as unknown as Omit<UseSuspenseQueryOptions, 'queryKey'>),
  }) as UseSuspenseQueryResult<TData, ResponseErrorConfig<GetAllTemplates500>> & { queryKey: TQueryKey }

  query.queryKey = queryKey as TQueryKey

  return query
}