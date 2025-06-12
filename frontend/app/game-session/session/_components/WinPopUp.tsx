import {Button, Dialog, DialogActions, DialogContent, DialogTitle, Grow} from "@mui/material";
import {removeGameSession} from "@/app/utils/game-session-handler.ts";
import {GAME_SESSION_APP_PATHS} from "@/app/constants/paths.ts";
import React, {useState} from "react";
import {useRouter} from "next/navigation";

export type WinPopUpProps = {
    roompin: string;
};

const WinPopUp: React.FC<WinPopUpProps> = ({ roompin }) => {

    const appRouterInstance = useRouter();
    const [showWinPopup, setShowWinPopup] = useState(true)

    return (
        <Dialog
            open={showWinPopup}
            TransitionComponent={Grow}
            transitionDuration={500}
            keepMounted
            onClose={() => setShowWinPopup(false)}
        >
            <DialogTitle>🎉 You Escaped! 🎉</DialogTitle>
            <DialogContent>
                You’ve solved the escape room. That was awesome!
            </DialogContent>
            <DialogActions>
                <Button onClick={() => {
                    setShowWinPopup(false)
                    removeGameSession()
                    appRouterInstance.push(`${GAME_SESSION_APP_PATHS.LEADERBOARD}/${roompin}`)
                }} autoFocus>
                    Close
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default WinPopUp;