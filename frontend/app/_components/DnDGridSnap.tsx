'use client'

import {DndContext} from "@dnd-kit/core";
import Droppable from "@/app/testpage/_components/Droppable.tsx";
import Draggable from "@/app/testpage/_components/Draggable.tsx";
import {useState} from "react";

const DnDGridSnap = () => {

    const [isDropped, setIsDropped] = useState(false)

    const draggableMarkup = <Draggable/>

    const handleDragEnd = (event: any) => {
        if (event.over && event.over.id === 'droppable') {
            setIsDropped(true)
        }
    }

    return (
        <DndContext autoScroll={false} onDragEnd={handleDragEnd}>
            {!isDropped ? draggableMarkup : null}
            <Droppable>
                {isDropped ? draggableMarkup : 'Drop here'}
            </Droppable>
        </DndContext>
    );
};

export default DnDGridSnap;