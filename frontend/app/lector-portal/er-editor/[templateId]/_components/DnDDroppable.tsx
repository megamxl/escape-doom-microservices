'use client'

import React from 'react';
import {useDroppable} from "@dnd-kit/core";
import {NodeDTO} from '@/app/gen/player';
import EditorNode from './EditorNode';
import {FileUpload} from "@mui/icons-material";
import {Button, Typography} from "@mui/material";

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
            {bgImageUrl ?
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
                </div> :
                <div className="border-2 border-dashed rounded-md h-full flex justify-center content-center">
                    <Button fullWidth={true} className="flex flex-col" sx={{ color: '#fff' }}>
                        <FileUpload sx={{fontSize: '6rem'}} />
                        <Typography variant="h6"> Click to add image </Typography>
                    </Button>
                </div>
            }
        </div>
    );
};

export default DnDDroppable;