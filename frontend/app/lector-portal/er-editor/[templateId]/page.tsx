import React from 'react';
import EscapeRoomEditor from "@/app/lector-portal/er-editor/[templateId]/EscapeRoomEditor.tsx";

type EditorProps = {
    params: Promise<{templateId: string}>
}

const EREditorPage = async ({params}: EditorProps) => {

    const templateId = (await params).templateId

    return (
        <EscapeRoomEditor templateId={templateId} />
    );
};

export default EREditorPage;