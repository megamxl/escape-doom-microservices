import {Stack, Typography} from "@mui/material";
import {blue} from "@mui/material/colors";

export const LeaderboardTitle = () => {
    return (
        <Stack sx={{backgroundColor: blue[400], borderRadius: "5px 5px 0px 0px"}} width={"100%"} height={"80px"} direction={"row"} justifyContent={"center"} alignItems={"center"}>
    <Typography align="center" fontSize={48}> LEADERBOARD </Typography>
        </Stack>
)
}