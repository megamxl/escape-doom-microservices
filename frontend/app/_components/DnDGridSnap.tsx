'use client'

import {DndContext, useDraggable} from "@dnd-kit/core";
import {CSS} from "@dnd-kit/utilities";

const DnDGridSnap = () => {

    const {attributes, listeners, setNodeRef, transform} = useDraggable({ id: 'button' })
    const style = {
        transform: CSS.Translate.toString(transform),
        color: '#000'
    }

    return (
        <>
            <DndContext>
                <div className="w-[100vw] h-[100vh] bg-amber-100">
                    <button ref={setNodeRef} style={style} {...listeners} {...attributes}>
                        I am button
                    </button>
                </div>
            </DndContext>
        </>
    );
};

export default DnDGridSnap;