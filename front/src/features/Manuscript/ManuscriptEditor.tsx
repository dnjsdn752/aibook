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

import { useQueryClient } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';

const ManuscriptEditor: React.FC = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [manuscriptId, setManuscriptId] = useState<number | null>(null);

    const queryClient = useQueryClient();
    const navigate = useNavigate();

    // AI 생성 결과 저장
    const [aiSummary, setAiSummary] = useState<string | null>(null);
    const [aiImage, setAiImage] = useState<string | null>(null);

    // 저장 (신규 등록)
    const handleTempSave = async () => {
        try {
            const response = await registerManuscript({ title, content });
            setManuscriptId(response.data.id);
            console.log('💾 저장 성공:', response.data);
        } catch (error) {
            console.error('저장 실패:', error);
        }
    };

    // 수정
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

    // AI 생성: 표지 + 요약
    const handleAiGenerate = async () => {
        if (!manuscriptId) {
            alert("먼저 원고를 저장해주세요.");
            return;
        }

        try {
            const res = await requestAi(manuscriptId);
            console.log("📄 AI 생성 성공:", res.data);

            // AI 결과 UI에 반영
            setAiSummary(res.data.aiSummary);
            setAiImage(res.data.aiImage);
        } catch (error) {
            console.error("AI 생성 요청 실패:", error);
            alert("AI 생성 요청 중 오류가 발생했습니다.");
        }
    };

    // 출간 요청
    const handlePublishRequest = async () => {
        if (!manuscriptId) {
            alert('먼저 원고를 저장해야 출간 요청이 가능합니다.');
            return;
        }
        try {
            const response = await requestPublishing(manuscriptId);
            console.log('📢 출간 요청 성공:', response.data);

            // ✅ 출간 요청 성공 후: 메인 페이지에서 목록 자동 갱신되게
            queryClient.invalidateQueries({ queryKey: ['books'] });

            alert('출간 요청이 완료되었습니다.');
            navigate('/'); // 메인 페이지로 이동
        } catch (error) {
            console.error('출간 요청 실패:', error);
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
                    <Button variant="outlined" onClick={handleAiGenerate}>
                        AI 생성
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
