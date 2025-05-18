import React from 'react';
import {useDraggable} from "@dnd-kit/core";
import {CSS} from '@dnd-kit/utilities';


const Draggable = () => {
    const {attributes, listeners, setNodeRef, transform} = useDraggable({
        id: 'draggable',
    })

    const style = {
        transform: CSS.Translate.toString(transform),
        color: '#fff'
    }

    return (
        <button ref={setNodeRef} style={style} {...listeners} {...attributes}>
            I am button :)
        </button>
    );
};

export default Draggable;