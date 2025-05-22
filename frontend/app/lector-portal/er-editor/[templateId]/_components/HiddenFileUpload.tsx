import React, {ChangeEvent} from 'react';

type HiddenFileUploadProps = {
    onChange: (event: ChangeEvent<HTMLInputElement>) => void
}

const HiddenFileUpload = ({onChange}: HiddenFileUploadProps) => {
    return (
        <input
            type="file"
            accept="image/*"
            onChange={onChange}
            style={{
                clip: 'rect(0,0,0,0)',
                clipPath: 'inset(50%)',
                height: 1,
                overflow: "hidden",
                position: 'absolute',
                bottom: 0,
                left: 0,
                whiteSpace: 'nowrap',
                width: 1
            }}
        />
    );
};

export default HiddenFileUpload;