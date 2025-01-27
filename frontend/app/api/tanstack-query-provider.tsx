'use client'

import React from "react";
import {queryClient} from "@/app/api/query-client";
import {QueryClientProvider} from "@tanstack/react-query";

export const ReactQueryProvider = ({children}: React.PropsWithChildren) => {
    return (
        <QueryClientProvider client={queryClient}>
            {children}
        </QueryClientProvider>
    )
}


