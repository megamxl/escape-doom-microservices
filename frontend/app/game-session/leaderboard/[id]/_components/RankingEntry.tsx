import {Box, Stack, Typography} from "@mui/material";
import {formatDate} from "@/app/game-session/leaderboard/[id]/formatDate";

type usrProps = {
    rank: number,
    name: string,
    points: number,
    time: number
}

export const RankingEntry: React.FC<usrProps> = ({rank, name, points, time}: usrProps) => {
    return (
        <Box width={"100%"}>
            <Stack direction="row" height={"60px"} alignItems={"center"} p={1}>
                <Typography fontSize={30} width={"100%"} ml={1}> {rank + 1}. </Typography>
                <Typography noWrap sx={{textOverflow: 'ellipsis'}} fontSize={24} width={"100%"}> {name} </Typography>
                <Typography fontSize={24} width={"100%"}> {points} </Typography>
                <Typography fontSize={24} width={"100%"}> {formatDate(time)} </Typography>
            </Stack>
        </Box>
    )
}