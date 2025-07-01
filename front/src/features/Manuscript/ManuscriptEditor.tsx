// src/features/Manuscript/ManuscriptEditor.tsx
import React, { useState } from 'react';
import {
    TextField,
    Button,
    Stack,
    Typography,
    Container,
} from '@mui/material';

import {
    registerManuscript,
    editManuscript,
    requestPublishing,
    requestAi,
} from '../../api/manuscript';

const ManuscriptEditor: React.FC = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [manuscriptId, setManuscriptId] = useState<number | null>(null);

    // 임시 저장 → 저장 (신규 등록)
    const handleTempSave = async () => {
        try {
            const response = await registerManuscript({ title, content });
            setManuscriptId(response.data.id);
            console.log('💾 저장(신규 등록) 성공:', response.data);
        } catch (error) {
            console.error('저장(신규 등록) 실패:', error);
        }
    };

    // 저장 → 수정
    const handleSave = async () => {
        if (!manuscriptId) {
            alert('먼저 저장 버튼을 눌러 원고를 등록해주세요.');
            return;
        }
        try {
            const response = await editManuscript(manuscriptId, { title, content });
            console.log('✏️ 수정 성공:', response.data);
        } catch (error) {
            console.error('수정 실패:', error);
        }
    };

    const handlePublishRequest = async () => {
        if (!manuscriptId) {
            alert('먼저 원고를 저장해야 출간 요청이 가능합니다.');
            return;
        }
        try {
            const response = await requestPublishing(manuscriptId);
            console.log('📢 출간 요청 성공:', response.data);
        } catch (error) {
            console.error('출간 요청 실패:', error);
        }
    };

    const handleGenerateCover = () => {
        console.log('🎨 표지 생성 (추후 API 연결 필요)');
    };

    const handleSummarize = async () => {
        if (!manuscriptId) {
            alert('먼저 원고를 저장해야 AI 요약 요청이 가능합니다.');
            return;
        }
        try {
            const response = await requestAi(manuscriptId);
            console.log('📄 요약 요청 성공:', response.data);
        } catch (error) {
            console.error('요약 요청 실패:', error);
        }
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
                        저장
                    </Button>
                    <Button variant="contained" color="secondary" onClick={handleSave}>
                        수정
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
