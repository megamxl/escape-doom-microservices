import React, {useEffect, useState} from 'react';
import {Button, InputLabel, MenuItem, Select, SelectChangeEvent, Stack} from "@mui/material";
import {useGetLevelHook, useGetSceneByIdHook, useUpdateNodeHook, ZoomNodeSpecificsDTO} from "@/app/gen/data";
import {NodeDTO, SceneDTO} from "@/app/gen/player";
import SaveIcon from "@mui/icons-material/Save";

type ZoomNodeProps = {
    node: NodeDTO,
    setNode:  React.Dispatch<React.SetStateAction<NodeDTO>>
}

const ZoomNodeForm = ({node, setNode}: ZoomNodeProps) => {
    const sceneId = node.scene_id ?? ''

    const [lvlId, setLvlId] = useState<string>()
    const [useableScenes, setUseableScenes] = useState<SceneDTO[]>([])

    const {data: sceneInfo} = useGetSceneByIdHook({sceneId: sceneId})
    const {refetch: loadLevelInfo} = useGetLevelHook({levelId: lvlId!}, {query: {enabled: false}})
    const {mutate: updateNode} = useUpdateNodeHook()

    useEffect(() => {
        if (!sceneInfo) return
        setLvlId(sceneInfo.level_id!)
    }, [sceneInfo]);

    useEffect(() => {
        const zoomLinks = node.node_specifics as ZoomNodeSpecificsDTO
        if (useableScenes.length > 0 && !zoomLinks.from) {
            zoomLinks.parent_scene_id = sceneId
            setNode(prev => ({...prev, node_specifics: zoomLinks}))
        }
    }, [useableScenes, sceneId, node.node_specifics, setNode]);

    useEffect(() => {
        if (!lvlId) return
        loadLevelInfo().then(res => {
            if (res.data && res.data.scenes) setUseableScenes(res.data.scenes)
            else {
                console.error("Level info was undefined!")
            }

        })
    }, [loadLevelInfo, lvlId]);

    const handleNodeUpdate = () => {
        if (!node.node_id) {
            console.error("This node has no node id:", node)
            return;
        }

        setNode(prev => ({...prev, node_specifics: node}))
        updateNode({nodeId: node.node_id, data: node}, {
            onSuccess: (response) => {
                console.log("Node updated successfully", response)
            }, onError: (error) => {
                console.error("Saving node didn't work:", error)
            }
        })
    }

    const handleParentLinkChange = (e: SelectChangeEvent) => {
        const zoomSpecifics = node.node_specifics as ZoomNodeSpecificsDTO
        console.log(zoomSpecifics)
        zoomSpecifics.parent_scene_id = e.target.value as string
        setNode(prev => ({...prev, node_specifics: zoomSpecifics}))
    }

    const handleChildLinkChange = (e: SelectChangeEvent) => {
        const zoomSpecifics = node.node_specifics as ZoomNodeSpecificsDTO
        console.log(zoomSpecifics)
        zoomSpecifics.linked_scene_id = e.target.value as string
        setNode(prev => ({...prev, node_specifics: zoomSpecifics}))
    }


    return (
        <>
            <Stack direction="row" spacing={4}>
                <div className="w-full">
                    <InputLabel> Parent Scene </InputLabel>
                    <Select
                        value={(node.node_specifics as ZoomNodeSpecificsDTO).parent_scene_id}
                        fullWidth
                        onChange={handleParentLinkChange}
                    >
                        {useableScenes.map(s => <MenuItem key={s.scene_id} value={s.scene_id}> {s.name} </MenuItem>)}
                    </Select>
                </div>
                <div className="w-full">
                    <InputLabel> Linked Scene </InputLabel>
                    <Select
                        value={(node.node_specifics as ZoomNodeSpecificsDTO).linked_scene_id}
                        onChange={handleChildLinkChange}
                        fullWidth
                    >
                        {useableScenes.filter(s => s.scene_id !== sceneId)
                            .map(s => <MenuItem key={s.scene_id} value={s.scene_id}> {s.name} </MenuItem>)}
                    </Select>
                </div>
            </Stack>
            <br/>
            <Button fullWidth variant="contained" startIcon={<SaveIcon/>} onClick={handleNodeUpdate}>
                Save
            </Button>
        </>
    );
};

export default ZoomNodeForm;