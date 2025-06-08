import React from 'react';
import {Button, FormControl, Stack, TextField} from "@mui/material";
import {NodeDTO, NodeSpecificsDTO} from "@/app/gen/player";
import SaveIcon from '@mui/icons-material/Save';
import ConsoleNodeForm from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/ConsoleNodeForm.tsx";
import {useUpdateNodeHook} from "@/app/gen/data";

type BasicNodeFormProps = {
    node: NodeDTO,
    setNode:  React.Dispatch<React.SetStateAction<NodeDTO>>
};

export type NodeSpecificProps = {
    nodeSpecifics: NodeSpecificsDTO
    setter: React.Dispatch<React.SetStateAction<NodeDTO>>
}

const BasicNodeForm = ({node, setNode}: BasicNodeFormProps) => {

    const {mutate: updateNode} = useUpdateNodeHook()

    const renderNodeSpecificForm = () => {
        switch (node.node_specifics?.node_type) {
            case "CONSOLE":
                return <ConsoleNodeForm nodeSpecifics={node.node_specifics} setter={setNode}/>
            case "DETAIL":
                return
        }
    }

    const handleNodeUpdate = () => {
        if (!node.node_id) {
            console.error("This node has no node id:", node)
            return;
        }
        updateNode({nodeId: node.node_id, data: node}, {
            onSuccess: (response) => {
                console.log("Node updated successfully", response)
            }, onError: (error) => {
                console.error("Saving node didn't work:", error)
            }
        })
    }

    return (
        <FormControl fullWidth>
            <Stack direction="row" spacing={4}>
                { node.node_specifics?.node_type !== "ZOOM" &&
                <Stack className="w-full" spacing={2}>
                    <TextField label="Title"
                               onChange={
                                   (e) => setNode(prev => ({...prev, title: e.target.value}))
                               }
                               value={node.title}
                               variant="outlined"
                               required/>
                    <TextField label="Description"
                               onChange={
                                   (e) => setNode(prev => ({...prev, description: e.target.value}))
                               }
                               variant="outlined"
                               value={node.description}
                               required/>
                </Stack> }
                {renderNodeSpecificForm()}
            </Stack>
            <br/>
            <Button variant="contained" startIcon={<SaveIcon/>} onClick={handleNodeUpdate}>
                Save
            </Button>
        </FormControl>
    );
};

export default BasicNodeForm;