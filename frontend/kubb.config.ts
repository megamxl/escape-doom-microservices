import { defineConfig } from '@kubb/core'
import { pluginOas } from '@kubb/plugin-oas'
import { pluginReactQuery } from '@kubb/plugin-react-query'
import { QueryKey } from '@kubb/plugin-react-query/components'
import { pluginTs } from '@kubb/plugin-ts'

/** @type {import('@kubb/core').UserConfig} */
export const config = {
    root: '.',
    input: {
        path: '../services/data-service/src/main/resources/api.yml',
    },
    output: {
        path: './app/gen',
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
                importPath: "@/axios-client-v2.ts"
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
            suspense: {}
        }),
    ],
}

export default defineConfig(config)