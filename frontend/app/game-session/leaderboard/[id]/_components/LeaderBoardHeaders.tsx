import {Box, Stack, Typography} from "@mui/material";

const headers = ["#", "Name", "Points", "Timestamp"]

export const LeaderboardHeaders = () => {
    return (
        <Box width={"100%"} sx={{backgroundColor: '#fff'}}>
            <Stack ml={1} direction="row" height={"40px"} alignItems={"center"} p={1}>
                {
                    headers.map((header, index) => (
                        <Typography key={index} fontWeight={"bold"} fontSize={24} color={'#000'}
                                    width={"100%"}> {header} </Typography>
                    ))
                }
            </Stack>
        </Box>
    )
}