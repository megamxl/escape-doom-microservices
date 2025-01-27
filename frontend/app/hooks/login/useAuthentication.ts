import {useQuery} from "@tanstack/react-query";
import {AuthCreds, getToken} from "@/app/api/lectorPortal/login";

export const useAuthentication = (creds: AuthCreds) => {
    return useQuery({
        queryKey: ["authentication", creds],
        queryFn: () => getToken(creds),
        enabled: false
    })
}