import {lectorClient} from "@/app/api/axios";

const ENDPOINT = "/auth/authenticate"

export type AuthCreds = {
    email: string,
    password: string
}

export type tokenCreatedResponse = {
    token: string
}

export const getToken = async (body: AuthCreds): Promise<tokenCreatedResponse> => {
    const { data } = await lectorClient.post(ENDPOINT, body);
    return data;
}