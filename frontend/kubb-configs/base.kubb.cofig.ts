import {defineConfig, UserConfig} from '@kubb/core'
import { pluginOas } from '@kubb/plugin-oas'
import { pluginReactQuery } from '@kubb/plugin-react-query'
import { QueryKey } from '@kubb/plugin-react-query/components'
import { pluginTs } from '@kubb/plugin-ts'


export function getConfig(identifier: string, postfix : string) : UserConfig {
    /** @type {import('@kubb/core').UserConfig} */
   return  {
        root: '.',
        input: {
            path: `../services/${identifier}-service/src/main/resources/api.yml`,
        },
        output: {
            path: `./app/gen/${identifier}`,
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
                    baseURL: `\${process.env.NEXT_PUBLIC_GW_URI}/${identifier}-api/${postfix}`
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
            }),
        ],
    }

}



