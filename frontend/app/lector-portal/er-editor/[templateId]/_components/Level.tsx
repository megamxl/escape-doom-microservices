import React, {useState} from 'react';
import {Box} from "@mui/material";
import { RemoveLevelProps } from '../EscapeRoomEditor';

type LevelProps = {
    title: string,
    onRemove: ({levelId}: RemoveLevelProps) => void;
    children?: React.ReactNode,
}

const Level = ({title, onRemove, children}: LevelProps) => {
    const [expanded, setExpanded] = useState(true);

    return (
        <Box>

        </Box>
    );
};

export default Level;