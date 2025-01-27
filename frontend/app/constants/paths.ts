import assert from "assert";

assert(process.env.NEXT_PUBLIC_LECTOR_PORTAL_BASE_URL, "env variable not set: NEXT_PUBLIC_LECTOR_PORTAL_BASE_URL")
assert(process.env.NEXT_PUBLIC_GAME_SESSION_BASE_URL, "env variable not set: NEXT_PUBLIC_GAME_SESSION_BASE_URL")
assert(process.env.NEXT_PUBLIC_WEB_URL, "env variable not set: NEXT_PUBLIC_WEB_URL")
assert(process.env.NEXT_PUBLIC_WEB_SOCKETS, "env variable not set: NEXT_PUBLIC_WEB_SOCKETS")

const SRV_PATH = process.env.NEXT_PUBLIC_WEB_URL

export class LECTOR_PORTAL_API {
    public static BASE_API = process.env.NEXT_PUBLIC_LECTOR_PORTAL_BASE_URL
    public static AUTH = `${this.BASE_API}/auth/authenticate`
}

export class LECTOR_PORTAL_APP_PATHS {
    public static BASE_PATH = `${SRV_PATH}/lector-portal`
    public static DASHBOARD = `${this.BASE_PATH}/dashboard`
    public static LOGIN = `${this.BASE_PATH}/login`

}

export class GAME_SESSION_API {
    public static BASE_API = process.env.NEXT_PUBLIC_GAME_SESSION_BASE_URL
    public static SESSION = `${this.BASE_API}/session`
    public static JOIN = `${this.BASE_API}/join`
}

export class GAME_SESSION_WEB_SOCKETS {
    public static BASE_API = process.env.NEXT_PUBLIC_WEB_SOCKETS
    public static YOUR_NAME = `${this.BASE_API}/ws/your-name`
    public static ALL_NAMES = `${this.BASE_API}/ws/all-names`
    public static STARTED = `${this.BASE_API}/ws/started`
}

export class GAME_SESSION_APP_PATHS {
    public static BASE_PATH = `${SRV_PATH}/game-session`
    public static STUDENT_JOIN = `${this.BASE_PATH}/student-join`
    public static SESSION = `${this.BASE_PATH}/session`
    public static LOBBY = `${this.BASE_PATH}/lobby`
    public static LEADERBOARD = `${this.BASE_PATH}/leaderboard`
}
