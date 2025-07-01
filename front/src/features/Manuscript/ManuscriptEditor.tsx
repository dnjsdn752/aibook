// src/features/Manuscript/ManuscriptEditor.tsx
import React, { useState } from 'react';

import {
    TextField,
    Button,
    Stack,
    Typography,
    Container,
    Box,
} from '@mui/material';

const ManuscriptEditor: React.FC = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleTempSave = () => {
        console.log('📝 임시 저장:', { title, content });
    };

    const handleSave = () => {
        console.log('💾 저장:', { title, content });
    };

    const handlePublishRequest = () => {
        console.log('📢 출간 요청');
    };

    const handleGenerateCover = () => {
        console.log('🎨 표지 생성');
    };

    const handleSummarize = () => {
        console.log('📄 요약 요청');
    };

    return (
        <Container maxWidth="md" sx={{ mt: 4 }}>
        <Stack spacing={3}>
            <Typography variant="h4">📚 집필 에디터</Typography>

            <TextField
            label="책 제목"
            fullWidth
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            />

            <TextField
            label="내용"
            fullWidth
            multiline
            minRows={15}
            value={content}
            onChange={(e) => setContent(e.target.value)}
            />

            <Stack direction="row" spacing={2} justifyContent="flex-end">
            <Button variant="outlined" onClick={handleGenerateCover}>
                표지 생성
            </Button>
            <Button variant="outlined" onClick={handleSummarize}>
                요약
            </Button>
            <Button variant="contained" onClick={handleTempSave}>
                임시 저장
            </Button>
            <Button variant="contained" color="secondary" onClick={handleSave}>
                저장
            </Button>
            <Button variant="contained" color="success" onClick={handlePublishRequest}>
                출간 요청
            </Button>
            </Stack>
        </Stack>
        </Container>
    );
};

export default ManuscriptEditor;
