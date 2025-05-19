import React, {useEffect, useRef, useState} from 'react';
import {TextField, TextFieldProps, Typography} from '@mui/material';

type InlineEditableTextProps = {
    value: string;
    onSave: (newValue: string) => void;
    variant?: React.ComponentProps<typeof Typography>['variant'];
    inputProps?: TextFieldProps;
}

const InlineEditableText = ({value, onSave, variant = 'body1', inputProps = {}}: InlineEditableTextProps) => {
    const [isEditing, setIsEditing] = useState(false);
    const [editingText, setEditingText] = useState(value);
    const [displayText, setDisplayText] = useState(value);
    const inputRef = useRef<HTMLInputElement>(null);

    // Update local state if the parent prop value changes
    useEffect(() => {
        if (!isEditing && value !== displayText) {
            setDisplayText(value);
            setEditingText(value);
        }
    }, [value, isEditing, displayText]);

    useEffect(() => {
        if (isEditing && inputRef.current) {
            inputRef.current.focus();
        }
    }, [isEditing]);

    const handleSave = () => {
        setIsEditing(false);
        setDisplayText(editingText);
        if (editingText !== value) {
            onSave(editingText);
        }
    };

    const handleCancel = () => {
        setIsEditing(false);
        setEditingText(displayText);
    };

    const handleEnterEditMode = () => {
        setEditingText(displayText);
        setIsEditing(true);
    };

    return isEditing ? (
        <TextField
            {...inputProps}
            variant={"outlined"}
            inputRef={inputRef}
            value={editingText}
            onChange={(e) => setEditingText(e.target.value)}
            onBlur={handleSave}
            onKeyDown={(e) => {
                if (e.key === 'Enter') {
                    handleSave();
                } else if (e.key === 'Escape') {
                    handleCancel();
                }
            }}
            size="small"
            fullWidth
        />
    ) : (
        <Typography
            variant={variant}
            onDoubleClick={handleEnterEditMode}
            className="truncate flex-grow min-w-0 cursor-pointer"
        >
            {displayText || <em style={{color: '#999'}}>Double click to edit</em>}
        </Typography>
    );
};

export default InlineEditableText;
