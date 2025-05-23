'use client'

import React, {useEffect, useMemo, useState} from 'react';
import {Box, Button, Divider, Grid, InputBase, Paper, Stack, Typography} from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import CheckIcon from '@mui/icons-material/Check';
import GridViewIcon from '@mui/icons-material/GridView';
import IconButton from "@mui/material/IconButton";
import {getERSessionByTagOrPinHook, SessionResponse, useGetERHistoryHook} from "@/app/gen/session";
import SessionCard from "@/app/lector-portal/dashboard/_components/SessionCard.tsx";
import SessionCardSkeleton from "@/app/lector-portal/dashboard/_components/SessionCardSkeleton.tsx";
import AddSessionFromTemplateCard from "@/app/lector-portal/dashboard/_components/AddSessionFromTemplateCard.tsx";
import CreateTemplateButton from "@/app/lector-portal/dashboard/_components/CreateTemplateButton.tsx";
import {useRouter} from "next/navigation";
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths.ts";
import {useGetAllTemplatesHook} from "@/app/gen/data";

const LectorPortalDashboard = () => {
    const router = useRouter()

    const { data: history, isLoading } = useGetERHistoryHook({});
    const [searchValue, setSearchValue] = useState('');
    const [searchResults, setSearchResults] = useState<SessionResponse[] | null>(null);
    const [selected, setSelected] = useState(
        new Map([
            ['open', true],
            ['started', false],
            ['finished', false],
            ['closed', false],
        ])
    );
    const { data: templates } = useGetAllTemplatesHook();


    useEffect(() => {
        if (searchValue.trim() === '') {
            setSearchResults(null);
        }
    }, [searchValue]);

    const redirectToTemplateView = () => {
        router.push(LECTOR_PORTAL_APP_PATHS.TEMPLATE_VIEW)
        router.refresh()
    }

    const handleFilterSelection = (state: string) => {
        setSearchResults(null);
        setSelected(prev => {
            const newMap = new Map(prev);
            newMap.set(state, !prev.get(state));
            return newMap;
        });
    };

    const clearFilters = () => {
        setSearchResults(null);
        const newMap = new Map<string, boolean>();
        for (const key of selected.keys()) {
            newMap.set(key, false);
        }
        setSelected(newMap);
    };

    const handleSearchSubmit = async () => {
        const trimmed = searchValue.trim();
        if (trimmed === '') {
            setSearchResults(null);
            return;
        }

        const allTrue = new Map<string, boolean>();
        selected.forEach((_, key) => allTrue.set(key, true));
        setSelected(allTrue);

        try {
            const params = /^\d{6}$/.test(trimmed)
                ? { pin: Number(trimmed) }
                : { tag: trimmed };

            const apiResults = await getERSessionByTagOrPinHook(params);
            setSearchResults(apiResults ?? []);
        } catch (err) {
            console.error('Search failed:', err);
            setSearchResults([]);
        }
    };

    const filteredSessions = useMemo(() => {
        const base = searchResults ?? history ?? [];
        const unique = Array.from(new Map(base.map(s => [s.session_id, s])).values());

        if (searchResults !== null) return unique;

        return unique.filter(s => selected.get(s.state ?? '') === true);
    }, [history, selected, searchResults]);

    return (
        <Box width="70vw" margin="auto" mt={6}>
            <Stack gap={3}>
                <Stack direction="row" alignItems="center" justifyContent="space-between">
                    <Typography fontSize="16" fontWeight="bold">Your Escape Rooms</Typography>
                    <Stack direction="row" gap={2}>
                        <Button
                            variant={"contained"}
                            onClick={redirectToTemplateView}
                            startIcon={<GridViewIcon/>}
                        >
                            View Templates
                        </Button>
                        <CreateTemplateButton/>
                    </Stack>
                </Stack>

                <Divider sx={{ flexGrow: 1, borderBottomWidth: 3 }} orientation="horizontal" />
                <Stack direction="row" gap={4} justifyContent="space-between">
                    <div>
                        {[...selected.keys()].map(state => (
                            <Button
                                key={state}
                                size="small"
                                variant="contained"
                                color="primary"
                                onClick={() => handleFilterSelection(state)}
                                sx={{mr: 1, px: 1.2, py: 0.4, fontSize: '0.8rem', fontWeight: 'bold', color: '#ffffff', '& .MuiSvgIcon-root': {color: '#ffffff', fontSize: '1rem', mr: 0.5,},}}
                            >
                                {selected.get(state) && <CheckIcon />}
                                {state.toUpperCase()}
                            </Button>
                        ))}
                        <Button color="primary" onClick={clearFilters} sx={{ fontSize: "0.7rem" }}>
                            Clear all filters
                        </Button>
                    </div>

                    <Paper
                        component="form"
                        onSubmit={e => {
                            e.preventDefault();
                            handleSearchSubmit().catch((error) => {
                                console.error(error);
                            });
                        }}
                        sx={{ p: '2px 4px', display: 'flex', alignItems: 'center', width: 350 }}
                    >
                        <InputBase
                            sx={{ ml: 1, flex: 1 }}
                            placeholder="Search by Pin or Tag"
                            value={searchValue}
                            onChange={e => setSearchValue(e.target.value)}
                        />
                        <IconButton type="submit" sx={{ p: '10px' }}>
                            <SearchIcon />
                        </IconButton>
                    </Paper>
                </Stack>

                <Grid container spacing={3}>
                    <Grid size={{ lg: 4, md: 6, sm: 12 }}>
                        <AddSessionFromTemplateCard onDone={() => setSearchResults(null)} />
                    </Grid>

                    {!isLoading
                        ? filteredSessions.map((session) => (
                            <Grid key={session.session_id} size={{ lg: 4, md: 6, sm: 12 }}>
                                <SessionCard
                                    session={session}
                                    templateName={templates?.find(t => t.template_id === session.template_id)?.name ?? 'Unknown'}
                                    onSessionUpdate={() => {}}
                                />
                            </Grid>
                        ))
                        : [...Array(6)].map((_, index) => (
                            <Grid key={index} size={{ lg: 4, md: 6, sm: 12 }}>
                                <SessionCardSkeleton />
                            </Grid>
                        ))}
                </Grid>
            </Stack>
        </Box>
    );
};

export default LectorPortalDashboard;