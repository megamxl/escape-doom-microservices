import { SxProps, Theme } from '@mui/material/styles';
import { Box } from "@mui/material";

interface Props {
    children?: React.ReactNode;
    sx?: SxProps<Theme>
}

const EditorContainer = ({sx = [], children}: Props) => {
    return (
        <>
            <Box sx={sx} mx={1} mt={1} p={1} bgcolor="#1e1e1e" minHeight={"50px"}>
                {children}
            </Box>
        </>
    );
}

export default EditorContainer;