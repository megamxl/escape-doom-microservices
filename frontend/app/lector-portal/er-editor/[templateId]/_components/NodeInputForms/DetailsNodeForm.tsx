import { Button, Typography } from '@mui/material';
import React from 'react';
import HiddenFileUpload from '../HiddenFileUpload';
import { FileUpload } from '@mui/icons-material';

const DetailsNodeForm = () => {

    const handleImageUpload = () => {

    }

    return (
            <Button
                component="label"
                role={undefined}
                tabIndex={-1}
                className="flex flex-col" sx={{color: '#fff'}}
                variant="outlined"
            >
                <HiddenFileUpload onChange={handleImageUpload}/>
                <FileUpload sx={{fontSize: '6rem'}}/>
                <Typography align={"center"} variant="h6"> Click to add image </Typography>
            </Button>
    );
};

export default DetailsNodeForm;