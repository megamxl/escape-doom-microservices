import React, {useEffect, useState} from 'react';
import {InputLabel, MenuItem, Select, Stack} from "@mui/material";
import {useGetLevelHook, useGetSceneByIdHook} from "@/app/gen/data";
import {SceneDTO} from "@/app/gen/player";

type ZoomNodeProps = {
    sceneId: string
}

const ZoomNodeForm = ({sceneId}: ZoomNodeProps) => {
    const [lvlId, setLvlId] = useState<string>()
    const [zoomLinks, setZoomLinks] = useState({from: sceneId, to: ''})
    const {data: sceneInfo} = useGetSceneByIdHook({sceneId: sceneId})

    const {refetch: loadLevelInfo} = useGetLevelHook({levelId: lvlId}, {query: {enabled: false}})
    const [useableScenes, setUseableScenes] = useState<SceneDTO[]>([])

    useEffect(() => {
        if (!sceneInfo) return
        setLvlId(sceneInfo.level_id!)
    }, [sceneInfo]);

    useEffect(() => {
        if (!lvlId) return
        loadLevelInfo().then(res => {
            if (res.data && res.data.scenes) setUseableScenes(res.data.scenes)
            else {
                console.error("Level info was undefined!")
            }

        })
    }, [loadLevelInfo, lvlId]);

    return (
        <Stack direction="row" spacing={4}>
            <div className="w-full">
                <InputLabel> Parent Scene </InputLabel>
                <Select
                    value={zoomLinks.from}
                    fullWidth
                    onChange={(e) => setZoomLinks(prev => ({...prev, from: e.target.value as string}))}
                >
                    {useableScenes.map(s => <MenuItem key={s.scene_id} value={s.scene_id}> {s.name} </MenuItem>)}
                </Select>
            </div>
            <div className="w-full">
                <InputLabel> Linked Scene </InputLabel>
                <Select
                    value={zoomLinks.to}
                    onChange={(e) => setZoomLinks(prev => ({...prev, to: e.target.value as string}))}
                    fullWidth
                >
                    {useableScenes.filter(s => s.scene_id !== sceneId).map(s => <MenuItem key={s.scene_id}
                                                                                          value={s.scene_id}> {s.name} </MenuItem>)}
                </Select>
            </div>
        </Stack>
    );
};

export default ZoomNodeForm;