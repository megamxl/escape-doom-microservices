import {redirect, RedirectType} from "next/navigation";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths";

export default function Home() {

    /**
     * Immediately redirects users to student-join if they call the base url
     */
    const redirectCall = async () => {
        redirect(GAME_SESSION_APP_PATHS.STUDENT_JOIN, RedirectType.push)
    }

    return (
        <>
            {redirectCall()}
        </>
    );
}
