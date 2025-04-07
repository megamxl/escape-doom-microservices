import React, {useState} from 'react';
import {Box, IconButton, Stack, Typography} from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import { RemoveLevelProps } from '../EscapeRoomEditor';

type LevelProps = {
    name: string,
    onRemove: ({levelId}: RemoveLevelProps) => void;
}

const Level = ({name, onRemove}: LevelProps) => {
    const [expanded, setExpanded] = useState(true);

    return (
        <Box>
            <Stack direction="row" alignItems="center" justifyContent="space-between">
                <Stack direction="row" alignItems="center" spacing={1}>
                    <IconButton
                        size="small"
                        onClick={() => setExpanded(!expanded)}
                        sx={{ color: 'white', transform: expanded ? 'rotate(90deg)' : 'rotate(0deg)', transition: '0.2s' }}
                    >
                        <ExpandMoreIcon />
                    </IconButton>
                    <Typography> {name} </Typography>
                </Stack>
                <Stack>

                </Stack>
            </Stack>

        </Box>
    );
};

export default Level;