import {useMutation} from "@tanstack/react-query"
import axios, {AxiosRequestConfig} from "axios"

export type ImgurCreationResponse = {
    status: number
    success: boolean
    data: {
        id: string
        deletehash: string
        name: string
        type: string
        width: number
        height: number
        size: number
        views: number
        bandwidth: number
        animated: boolean
        favorite: boolean
        in_gallery: boolean
        in_most_viral: boolean
        has_sound: boolean
        is_ad: boolean
        link: string
        tags: Array<string>
        datetime: number
        mp4: string
        hls: string
    }
}

export const useUploadImageToImgur = () => {

    return useMutation({
        retry: 3,
        mutationKey: ['postImgurImage'],
        mutationFn: async (file: File): Promise<ImgurCreationResponse> => {

            const url = `https://api.imgur.com/3/image?client_id`
            const queryParams: AxiosRequestConfig = {
                headers: {
                    Authorization: `Client-ID ${process.env.NEXT_PUBLIC_IMGUR_CLIENT_ID}`,
                },
            }

            const formData = new FormData();
            formData.append('image', file)

            const {data} = await axios.post(url, formData, queryParams);

            return data
        }
    })
}