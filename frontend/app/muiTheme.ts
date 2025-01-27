'use client'
import { createTheme } from "@mui/material/styles";

//TODO: Adjust Dark Theme Palette to match expectations
export const muiTheme = createTheme({
    palette: {
        mode: 'dark'
    },
    typography: {
        fontFamily: 'var(--font-roboto)'
    },
})