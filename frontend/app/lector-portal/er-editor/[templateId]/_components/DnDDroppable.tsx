'use client'

import React from 'react';
import {useDroppable} from "@dnd-kit/core";
import {NodeDTO} from '@/app/gen/player';
import EditorNode from './EditorNode';

type DroppableProps = {
    bgImageUrl?: string
    nodeList: NodeDTO[]
}

const DnDDroppable = ({nodeList, bgImageUrl}: DroppableProps) => {
    const {isOver, setNodeRef} = useDroppable({
        id: 'droppable'
    })

    const style = isOver ? 'border-2 border-dashed rounded-md' : ""


    return (
        <div className="grow w-full h-full">
            <div className="relative">
                <img
                    ref={setNodeRef}
                    src={bgImageUrl}
                    alt="Scene Background"
                    className={`${style} w-full bg-no-repeat bg-contain z-0`}>
                </img>
                {nodeList.map(node => {
                    return (
                        <EditorNode key={node.node_id} node={node}/>
                    )
                })}
            </div>
        </div>
    );
};

export default DnDDroppable;