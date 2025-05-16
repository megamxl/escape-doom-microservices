'use client'

import React from 'react';
import {useDroppable} from "@dnd-kit/core";
import {NodeDTO} from '@/app/gen/player';
import EditorNode from './EditorNode';

type DroppableProps = {
    bgImageUrl?: string
    nodeList: NodeDTO[]
}

const DnDDroppable = ({nodeList}: DroppableProps) => {
    const {isOver, setNodeRef} = useDroppable({
        id: 'droppable'
    })

    const style = isOver ? 'border-4 border-dashed rounded-md'
        : 'border-2 border-dashed rounded-md'


    return (
        <div ref={setNodeRef} className={`${style} grow h-full relative`}>
            {nodeList.map(node => {
                return (
                    <EditorNode key={node.node_id} node={node}/>
                )
            })}
        </div>
    );
};

export default DnDDroppable;