import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import {Skeleton} from "@mui/material";


const SkeletonRoomCard = () => {

    return (
        <Card>
            <CardMedia>
                <Skeleton variant="rounded" height={250} />
            </CardMedia>
            <CardContent>
                <Typography gutterBottom variant="h5" component="div" width={200}> <Skeleton /> </Typography>
                <Typography gutterBottom sx={{fontSize: 14}} component="div" width={100}> <Skeleton /> </Typography>
            </CardContent>
            <CardActions sx={{justifyContent: "space-between"}}>
                <Skeleton variant="circular" width={30} height={30} />
                <Skeleton variant="rounded" width={65 + 16} height={24 + 12} />
                <Skeleton variant="rounded" width={65 + 16} height={24 + 12} />
                <Skeleton variant="rounded" width={65 + 16} height={24 + 12} />
                <Skeleton variant="rounded" width={65 + 16} height={24 + 12} />
            </CardActions>
        </Card>
    );
};

export default SkeletonRoomCard;