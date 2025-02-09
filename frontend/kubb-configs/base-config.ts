import {UserConfig} from '@kubb/core'
import {pluginOas} from '@kubb/plugin-oas'
import {pluginReactQuery} from '@kubb/plugin-react-query'
import {QueryKey} from '@kubb/plugin-react-query/components'
import {pluginTs} from '@kubb/plugin-ts'

export const loadConfigWithPath = (pathName: string): UserConfig => {
    //@ts-ignore
    config.input.path = pathName;
    return config
}

/** @type {import('@kubb/core').UserConfig} */
const config: UserConfig = {
    root: '.',
    input: {
        path: '',
    },
    output: {
        path: './app/gen',
        clean: true
    },
    plugins: [
        pluginOas({
            serverIndex: 0
        }),
        pluginTs({
            output: {
                path: 'models',
                banner(oas) {
                    return `// version: ${oas.api.info.version}`
                },
            },
        }),
        pluginReactQuery({
            // client: {
            //     importPath: "@/app/api/axios-client-v2"
            // },
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

export default loadConfigWithPath