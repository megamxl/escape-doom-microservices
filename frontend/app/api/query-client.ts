import { QueryClient } from "@tanstack/react-query";

const defaultQueryConfig = { staleTime: 60 * 1000 }

export const queryClient = new QueryClient({
    defaultOptions: {queries: defaultQueryConfig}
})