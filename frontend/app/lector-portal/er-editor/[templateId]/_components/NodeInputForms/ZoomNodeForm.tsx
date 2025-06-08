'use client'

import React, {useEffect, useState} from 'react';
import {Alert, Button, InputLabel, MenuItem, Select, SelectChangeEvent, Snackbar, Stack} from "@mui/material";
import {
    useDeleteNodeHook,
    useGetLevelHook,
    useGetSceneByIdHook,
    useUpdateNodeHook,
    ZoomNodeSpecificsDTO
} from "@/app/gen/data";
import {NodeDTO, SceneDTO} from "@/app/gen/player";
import SaveIcon from "@mui/icons-material/Save";
import DeleteIcon from "@mui/icons-material/Delete";

type ZoomNodeProps = {
    node: NodeDTO,
    setNode: React.Dispatch<React.SetStateAction<NodeDTO>>,
    onDeletion: (nodeId: string) => void
}

const ZoomNodeForm = ({node, onDeletion, setNode}: ZoomNodeProps) => {

    const [showSnackbar, setShowSnackbar] = useState({success: false, error: false})
    const [lvlId, setLvlId] = useState<string>()
    const [useableScenes, setUseableScenes] = useState<SceneDTO[]>([])

    const {data: sceneInfo} = useGetSceneByIdHook({sceneId: node.scene_id ?? ''})
    const {refetch: loadLevelInfo} = useGetLevelHook({levelId: lvlId!}, {query: {enabled: false}})
    const {mutate: updateNode} = useUpdateNodeHook()
    const {mutate: removeNode} = useDeleteNodeHook()

    useEffect(() => {
        if (!sceneInfo) return
        setLvlId(sceneInfo.level_id!)
    }, [sceneInfo]);

    useEffect(() => {
        if (!lvlId) return
        loadLevelInfo().then(res => {
            if (res.data && res.data.scenes) {
                setUseableScenes(res.data.scenes)
            } else {
                console.error("Level info was undefined!")
            }

        })
    }, [loadLevelInfo, lvlId]);

    const handleNodeUpdate = () => {
        if (!node.node_id) {
            console.error("This node has no node id:", node)
            return;
        }

        updateNode({nodeId: node.node_id, data: node}, {
            onSuccess: () => {
                setShowSnackbar(prev => ({...prev, success: true}))
            }, onError: (error) => {
                setShowSnackbar(prev => ({...prev, error: true}))
                console.error("Saving node didn't work:", error)
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

    const handleParentLinkChange = (e: SelectChangeEvent) => {
        const zoomSpecifics = node.node_specifics as ZoomNodeSpecificsDTO
        zoomSpecifics.parent_scene_id = e.target.value as string
        setNode(prev => ({...prev, node_specifics: zoomSpecifics}))
    }

    const handleChildLinkChange = (e: SelectChangeEvent) => {
        const zoomSpecifics = node.node_specifics as ZoomNodeSpecificsDTO
        zoomSpecifics.linked_scene_id = e.target.value as string
        setNode(prev => ({...prev, node_specifics: zoomSpecifics}))
    }

    if (!useableScenes.length) return <div>Loading scenes...</div>

    return (
        <>
            <Stack direction="row" spacing={4}>
                <div className="w-full">
                    <InputLabel> Parent Scene </InputLabel>
                    <Select
                        value={(node.node_specifics as ZoomNodeSpecificsDTO).parent_scene_id ?? ""}
                        fullWidth
                        onChange={handleParentLinkChange}
                    >
                        {useableScenes.map(s => <MenuItem key={s.scene_id} value={s.scene_id}> {s.name} </MenuItem>)}
                    </Select>
                </div>
                <div className="w-full">
                    <InputLabel> Linked Scene </InputLabel>
                    <Select
                        value={(node.node_specifics as ZoomNodeSpecificsDTO).linked_scene_id ?? ""}
                        onChange={handleChildLinkChange}
                        fullWidth
                    >
                        {useableScenes.filter(s => s.scene_id !== node.scene_id)
                            .map(s => <MenuItem key={s.scene_id} value={s.scene_id}> {s.name} </MenuItem>)}
                    </Select>
                </div>
            </Stack>
            <br/>
            <Stack direction="row" spacing={4}>
                <Button fullWidth variant="contained" color="error" startIcon={<DeleteIcon/>}
                        onClick={handleNodeDeletion}>
                    Delete
                </Button>
                <Button fullWidth variant="contained" color="success" startIcon={<SaveIcon/>}
                        onClick={handleNodeUpdate}>
                    Save
                </Button>
            </Stack>
            <Snackbar
                open={showSnackbar.success}
                autoHideDuration={1000}
                onClose={() => setShowSnackbar(prev => ({...prev, success: false}))}>
                <Alert
                    onClose={() => setShowSnackbar(prev => ({...prev, success: false}))}
                    severity="success"
                    variant="filled"
                    sx={{width: '100%'}}
                >
                    Node was saves successfully!
                </Alert>
            </Snackbar>
            <Snackbar
                open={showSnackbar.error}
                autoHideDuration={1000}
                onClose={() => setShowSnackbar(prev => ({...prev, error: false}))}>
                <Alert
                    onClose={() => setShowSnackbar(prev => ({...prev, error: false}))}
                    severity="error"
                    variant="filled"
                    sx={{width: '100%'}}
                >
                    Saving node failed!
                </Alert>
            </Snackbar>
        </>
    );
};

export default ZoomNodeForm;