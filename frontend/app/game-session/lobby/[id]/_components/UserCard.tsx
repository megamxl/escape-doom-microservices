import {Typography} from "@mui/material";
import { blue } from "@mui/material/colors";

interface Props {
    playerName: string,
    isMainUsr: boolean
}

const UserCard = ({playerName, isMainUsr}: Props) => {
    return (
        <>
            <Typography align="center" color={isMainUsr ? blue[600] : '#fff'} fontSize={24} fontWeight={"bold"}> {playerName} </Typography>
        </>
);
}

export default UserCard;