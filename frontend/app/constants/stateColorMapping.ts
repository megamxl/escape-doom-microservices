import {EscapeRoomState} from "@/app/gen/session";
import {green, pink, red, yellow} from "@mui/material/colors";

const stateColorMapping = (state: EscapeRoomState): string => {
    switch (state) {
        case "open": return yellow[500]
        case "closed": return red[500]
        case "finished": return pink[700]
        case "started": return green[500]
    }
}

export default stateColorMapping