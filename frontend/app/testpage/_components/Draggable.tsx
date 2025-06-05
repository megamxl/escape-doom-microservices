import React, {CSSProperties, useState} from 'react';
import {useDraggable} from "@dnd-kit/core";

export type DraggableProps = {
    id: string,
    type: string,
    title: string
}

export type Coordinates = {
    x: number;
    y: number;
};

const defaultCoordinates = {
    x: 0,
    y: 0,
};

const Draggable = ({id, title, type}: DraggableProps) => {
    const {
        attributes,
        listeners,
        setNodeRef,
        transform,
        isDragging
    } = useDraggable({
        id: 'draggable',
        data: {
            id,
            type,
            title
        }
    })

    const [{x, y}, setCoordinates] = useState<Coordinates>(defaultCoordinates)

    const style: CSSProperties | undefined = isDragging
        ? {
            position: "absolute",
            transform: `translate3d(${transform?.x}px, ${transform?.y}px, 0)`,
            cursor: "move"
        } : {
            cursor: "pointer"
        }

    return (
        <>
            <div ref={setNodeRef} style={style} {...listeners} {...attributes}>
                {title}
            </div>
        </>

        // <button ref={setNodeRef} style={style} {...listeners} {...attributes}>
        //     I am button :)
        // </button>
    );
};

export default Draggable;