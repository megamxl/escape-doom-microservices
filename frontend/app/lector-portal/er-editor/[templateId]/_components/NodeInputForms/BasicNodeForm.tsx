'use client'

import React from 'react';
import {Button, FormControl, Stack, TextField} from "@mui/material";
import {NodeDTO} from "@/app/gen/player";
import SaveIcon from '@mui/icons-material/Save';
import DeleteIcon from '@mui/icons-material/Delete';
import ConsoleNodeForm from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/ConsoleNodeForm.tsx";
import {useCreateNodeHook, useDeleteNodeHook, useUpdateNodeHook} from "@/app/gen/data";
import DetailsNodeForm from "@/app/lector-portal/er-editor/[templateId]/_components/NodeInputForms/DetailsNodeForm.tsx";

type BasicNodeFormProps = {
    node: NodeDTO,
    setNode:  React.Dispatch<React.SetStateAction<NodeDTO>>
    onDeletion: (nodeId: string) => void
};

const BasicNodeForm = ({node, onDeletion, setNode}: BasicNodeFormProps) => {

    const {mutate: updateNode} = useUpdateNodeHook()
    const {mutate: createNode} = useCreateNodeHook()
    const {mutate: removeNode} = useDeleteNodeHook()

    const renderNodeSpecificForm = () => {
        switch (node.node_specifics?.node_type) {
            case "CONSOLE":
                return <ConsoleNodeForm nodeSpecifics={node.node_specifics} setter={setNode}/>
            case "DETAIL":
                return <DetailsNodeForm />
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
                if (error.status !== 400) {
                    console.error("Saving node didn't work:", error, node)
                    return;
                }
                createNode({data: node}, {
                    onSuccess: (response) => {
                        console.log("Created node", response)
                    },
                    onError: (createError) => {
                        console.error("Creating node didn't work", createError, node)
                    }
                })
            }
        })
    }

    const handleNodeDeletion = () => {
        removeNode({nodeId: node.node_id ?? ''}, {
            onSuccess: () => {
                onDeletion(node.node_id ?? '')
            },
            onError: (error) => {
                console.error("Deleting node failed", error)
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
                               value={node.title ?? ''}
                               variant="outlined"
                               required/>
                    <TextField label="Description"
                               onChange={
                                   (e) => setNode(prev => ({...prev, description: e.target.value}))
                               }
                               multiline
                               minRows={2}
                               variant="outlined"
                               value={node.description  ?? ''}
                               required/>
                </Stack> }
                {renderNodeSpecificForm()}
            </Stack>
            <br/>
            <Stack direction="row" spacing={4}>
                <Button fullWidth variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={handleNodeDeletion}>
                    Delete
                </Button>
                <Button fullWidth variant="contained" color="success" startIcon={<SaveIcon/>} onClick={handleNodeUpdate}>
                    Save
                </Button>
            </Stack>
        </FormControl>
    );
};

export default BasicNodeForm;