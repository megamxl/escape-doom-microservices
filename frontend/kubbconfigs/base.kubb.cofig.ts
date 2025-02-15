import {defineConfig, UserConfig} from '@kubb/core'
import { pluginOas } from '@kubb/plugin-oas'
import { pluginReactQuery } from '@kubb/plugin-react-query'
import { QueryKey } from '@kubb/plugin-react-query/components'
import { pluginTs } from '@kubb/plugin-ts'


export function getConfig(indentifier: string, postfix : string) : UserConfig {
    /** @type {import('@kubb/core').UserConfig} */
   return  {
        root: '.',
        input: {
            path: `../services/${indentifier}-service/src/main/resources/api.yml`,
        },
        output: {
            path: `./app/gen/${indentifier}`,
            clean: true
        },
        plugins: [
            pluginOas({ generators: [] }),
            pluginTs({
                output: {
                    path: 'models',
                    banner(oas) {
                        return `// version: ${oas.api.info.version}`
                    },
                },
            }),
            pluginReactQuery({
                client: {
                    baseURL: `\${process.env.NEXT_PUBLIC_GW_URI}/${indentifier}-api/${postfix}`
                },
                transformers: {
                    name: (name, type) => {
                        if (type === 'file' || type === 'function') {
                            return `${name}Hook`
                        }
                        return name
                    },
                },
                output: {
                    path: './hooks',
                },
                group: {
                    type: 'path',
                },
                queryKey(props) {
                    const keys = QueryKey.getTransformer(props)
                    return ['"v1"', ...keys]
                },
                paramsType: 'inline',
                pathParamsType: 'object',
                suspense: {},
                override: [
                    {
                        type: 'operationId',
                        pattern: 'findPetsByTags',
                        options: {
                            client: {
                                dataReturnType: 'full',
                                importPath: '@kubb/plugin-client/clients/axios',
                            },
                            infinite: {
                                queryParam: 'pageSize',
                                initialPageParam: 0,
                                cursorParam: undefined,
                            },
                        },
                    },
                    {
                        type: 'operationId',
                        pattern: 'getInventory',
                        options: {
                            query: false,
                        },
                    },
                    {
                        type: 'operationId',
                        pattern: 'updatePetWithForm',
                        options: {
                            query: {
                                importPath: '@tanstack/react-query',
                                methods: ['post'],
                            },
                            mutation: {
                                importPath: '@tanstack/react-query',
                                methods: ['put', 'delete'],
                            },
                            pathParamsType: 'inline',
                        },
                    },
                ],
            }),
        ],
    }

}



