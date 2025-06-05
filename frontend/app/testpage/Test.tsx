'use client'

import React, {useEffect, useState} from "react";
import {Grid} from "@mui/material";
import {DndContext, DragEndEvent} from "@dnd-kit/core";
import Droppable from "@/app/testpage/_components/Droppable.tsx";
import Draggable, {DraggableProps} from "@/app/testpage/_components/Draggable.tsx";
import {nanoid} from "nanoid";

export default function Test() {
    const [elements, setElements] = useState<DraggableProps[]>([])

    useEffect(() => {
        console.log(elements)
    }, [elements]);

    const handleDragEnd = (event: DragEndEvent) => {
        console.log('Event:', event)
        if (event.over) {

            const newElements = [...elements, ...[event.active.data.current]]
            setElements(newElements)
        }
    }

    const renderElement = () => {
        return elements.map((el, idx) => {
            return <div key={idx}> {el.title} </div>
        })
    }

    return (
        <>
            <Grid spacing={4} container p={2} height={'100vh'} style={{backgroundColor: '#121212'}}>
                <Grid size='grow' style={{backgroundColor: '#1e1e1e'}} className="p-4 relative h-full">
                    <DndContext autoScroll={false} onDragEnd={handleDragEnd}>
                        <Droppable>
                            {renderElement()}
                        </Droppable>
                        <div id="NodesContainer"
                             className="p-2 rounded-lg z-10 bg-[#2e2e2e] flex justify-center gap-4 absolute bottom-4 left-[50%] translate-x-[-50%]">
                            <Draggable id={nanoid(11)} type={"button"} title={"Drag me :3"} />
                            <Draggable id={nanoid(11)} type={"button"} title={"Drag me :3"} />
                            <Draggable id={nanoid(11)} type={"button"} title={"Drag me :3"} />
                            <Draggable id={nanoid(11)} type={"button"} title={"Drag me :3"} />
                        </div>
                    </DndContext>
                </Grid>
            </Grid>
            {/*<DnDGridSnap />*/}
            {/*<Button onClick={handleClick}> Send stuff </Button>*/}
        </>
    );
}
