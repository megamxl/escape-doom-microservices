import Axios from "axios"
import {GAME_SESSION_API, LECTOR_PORTAL_API} from "@/app/constants/paths";

// Asserts to not run if .env Variables are not prominent


// Exports two different Axios clients due to two different base URLs and use-cases
export const lectorClient = Axios.create({
    baseURL: LECTOR_PORTAL_API.BASE_API
})

export const gameSessionClient = Axios.create({
    baseURL: GAME_SESSION_API.BASE_API
})