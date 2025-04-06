import React from 'react';
import {Card, CardContent, Typography, Button, Stack, Box, Divider, Paper} from '@mui/material';

export type ErrorDetails = {
    timestamp: string;
    status: number;
    error: string;
    message: string;
    path: string;
};

export type ErrorDisplayCardProps = {
    errorDetails: ErrorDetails;
    onRetry: () => void;
    onBack: () => void;
};

function ErrorOutlineIcon(props: { color: string, sx: { fontSize: number } }) {
    return null;
}

const ErrorDisplayCard: React.FC<ErrorDisplayCardProps> = ({ errorDetails, onRetry, onBack }) => {
    return (
        <Stack height="100vh" justifyContent="center" alignItems="center">
            <Paper elevation={4} sx={{ borderRadius: 4, maxWidth: 600, p: 4 }}>
                <Stack direction="row" alignItems="center" spacing={3} mb={3}>
                    <ErrorOutlineIcon color="error" sx={{ fontSize: 48 }} />
                    <Box>
                        <Typography variant="h4" fontWeight="bold" gutterBottom>
                            Oops! Something went wrong.
                        </Typography>
                        <Typography color="text.secondary">
                            We encountered an issue while trying to process your request.
                        </Typography>
                    </Box>
                </Stack>

                <Divider sx={{ my: 3 }} />

                <Card variant="outlined" sx={{ borderColor: 'error.light', p: 3, mb: 3 }}>
                    <Typography variant="subtitle1" fontWeight="bold" color="error.main" gutterBottom>
                        Detailed Error Information:
                    </Typography>
                    <Typography variant="body2" color="error.dark" mb={1}>
                        <strong>Message:</strong> {errorDetails.message}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" mb={1}>
                        <strong>Status:</strong> {errorDetails.status} {errorDetails.error && `(${errorDetails.error})`}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" mb={1}>
                        <strong>Time:</strong> {new Date(errorDetails.timestamp).toLocaleString()}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        <strong>Path:</strong> {errorDetails.path}
                    </Typography>
                </Card>

                <Stack direction="row" spacing={2} justifyContent="flex-end">
                    <Button variant="outlined" onClick={onBack}>
                        Go Back
                    </Button>
                    <Button variant="contained" color="error" onClick={onRetry}>
                        Retry
                    </Button>
                </Stack>
            </Paper>
        </Stack>
    );
};

export default ErrorDisplayCard;
