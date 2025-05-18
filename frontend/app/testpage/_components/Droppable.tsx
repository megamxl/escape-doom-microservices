import React, {ReactNode} from 'react';
import {useDroppable} from "@dnd-kit/core";

type DroppableProps = {
    children?: ReactNode
}

const Droppable = ({children}: DroppableProps) => {
    const {isOver, setNodeRef} = useDroppable({
        id: 'droppable',
    });

    const style = {
        color: isOver ? 'green' : 'red'
    }

    return (
        <div className="w-[100vw] h-[100vh] border-2 border-dashed" ref={setNodeRef} style={style}>
            {children}
        </div>
    );
};

export default Droppable;