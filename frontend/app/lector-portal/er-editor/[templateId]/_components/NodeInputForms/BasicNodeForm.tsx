import React, {useState} from 'react';
import {FormControl, Stack, TextField} from "@mui/material";
import {NodeDTO, NodeSpecificsDTO} from "@/app/gen/player";
import ConsoleNodeForm from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/ConsoleNodeForm.tsx";

type BasicNodeForm = {
    node: NodeDTO
}

export type NodeSpecificProps = {
    nodeSpecifics: NodeSpecificsDTO
    setter: React.Dispatch<React.SetStateAction<NodeDTO>>
}

const BasicNodeForm = ({node}: BasicNodeForm) => {
    const [nodeState, setNodeState] = useState(node)

    const renderNodeSpecificForm = () => {
        switch (nodeState.node_specifics?.node_type) {
            case "CONSOLE":
                return <ConsoleNodeForm nodeSpecifics={nodeState.node_specifics} setter={setNodeState}/>
            case "DETAIL":
                return
        }
    }

    return (
        <FormControl fullWidth>
            <Stack direction="row" spacing={4}>
                { nodeState.node_specifics?.node_type !== "ZOOM" &&
                <Stack className="w-full" spacing={2}>
                    <TextField label="Title"
                               onChange={
                                   (e) => setNodeState(prev => ({...prev, title: e.target.value}))
                               }
                               value={nodeState.title}
                               variant="outlined"
                               required/>
                    <TextField label="Description"
                               onChange={
                                   (e) => setNodeState(prev => ({...prev, title: e.target.value}))
                               }
                               variant="outlined"
                               value={nodeState.description}
                               required/>
                </Stack> }
                {renderNodeSpecificForm()}
            </Stack>
        </FormControl>
    );
};

export default BasicNodeForm;