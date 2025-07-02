import React, { useState, useEffect, useRef } from 'react';
import {
    TextField,
    Button,
    Stack,
    Typography,
    Container,
    CircularProgress,
    Modal,
    Box,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
} from '@mui/material';
import {
    registerManuscript,
    editManuscript,
    requestPublishing,
    requestAi,
    getManuscript
} from '../../api/manuscript';

import { useQueryClient } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';

const ManuscriptEditor: React.FC = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [manuscriptId, setManuscriptId] = useState<number | null>(null);

    const queryClient = useQueryClient();
    const navigate = useNavigate();
    const resultRef = useRef<HTMLDivElement | null>(null);

    // AI 생성 결과 저장
    const [aiSummary, setAiSummary] = useState<string | null>(null);
    const [aiImage, setAiImage] = useState<string | null>(null);
    const [loadingAi, setLoadingAi] = useState<boolean>(false);
    const [openModal, setOpenModal] = useState(false);

    useEffect(() => {
    if (!loadingAi && aiSummary && resultRef.current) {
        resultRef.current.scrollIntoView({ behavior: 'smooth' });
        }
    }, [loadingAi, aiSummary]);

    // 저장 (신규 등록)
    const handleTempSave = async () => {
        try {
            const response = await registerManuscript({ title, content });
            setManuscriptId(response.data.id);
            alert("저장 완료!");
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
            alert("수정 완료!");
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
            setLoadingAi(true);
            await requestAi(manuscriptId);
            console.log("📡 AI 요청 완료. 결과 대기 중...");

            let attempts = 0;
            const maxAttempts = 60;
            const pollInterval = 2000;

            const pollForResult = async () => {
                try {
                    const res = await getManuscript(manuscriptId);
                    console.log("📦 getManuscript polling 응답:", res.data);

                    const hasResult = !!res.data?.aiSummary || !!res.data?.aiImage;

                    if (hasResult) {
                        setAiSummary(res.data.aiSummary);
                        setAiImage(res.data.aiImage);
                        setOpenModal(true);
                        setLoadingAi(false);
                    } else {
                        attempts++;
                        if (attempts < maxAttempts) {
                            setTimeout(pollForResult, pollInterval);
                        } else {
                            alert("AI 생성 시간이 초과되었습니다. 잠시 후 다시 시도해주세요.");
                            setLoadingAi(false);
                        }
                    }
                } catch (err) {
                    console.error("❌ getManuscript 실패:", err);
                    alert("결과 확인 중 오류가 발생했습니다.");
                    setLoadingAi(false);
                }
            };

            pollForResult(); // ✅ 실제로 호출해야 polling이 시작됩니다!
        } catch (error) {
            console.error("AI 생성 요청 실패:", error);
            alert("AI 생성에 실패했습니다.");
            setLoadingAi(false);
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

    const handleModalClose = () => {
        setOpenModal(false);
    };

    const handleRetry = () => {
        setAiSummary(null);
        setAiImage(null);
        setOpenModal(false);

        handleAiGenerate();
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
            <Button
                variant="outlined"
                onClick={handleAiGenerate}
                disabled={loadingAi}
            >
                {loadingAi ? 'AI 생성 중...' : 'AI 생성'}
            </Button>
            <Button variant="contained" onClick={handleTempSave}>
                저장
            </Button>
            <Button variant="contained" color="secondary" onClick={handleSave}>
                수정
            </Button>
            <Button
                variant="contained"
                color="success"
                onClick={handlePublishRequest}
            >
                출간 요청
            </Button>
            </Stack>
        </Stack>

        {/* ✅ 중앙에 스피너 표시 */}
        {loadingAi && (
            <Stack
            sx={{
                position: 'fixed',
                top: 0,
                left: 0,
                width: '100vw',
                height: '100vh',
                zIndex: 2000,
                backgroundColor: 'rgba(255,255,255,0.6)',
            }}
            justifyContent="center"
            alignItems="center"
            >
            <CircularProgress size={60} />
            <Typography mt={2}>AI 결과 생성 중입니다...</Typography>
            </Stack>
        )}

        {/* ✅ AI 결과 모달 */}
        <Dialog open={openModal} onClose={handleModalClose} fullWidth maxWidth="sm">
            <DialogTitle>🧠 AI 생성 결과</DialogTitle>
            <DialogContent dividers>
            {aiSummary && (
                <Stack spacing={1} mb={2}>
                <Typography variant="subtitle1">📄 요약</Typography>
                <Typography>{aiSummary}</Typography>
                </Stack>
            )}
            {aiImage && (
                <Stack spacing={1}>
                <Typography variant="subtitle1">🎨 표지</Typography>
                <img
                    src={aiImage}
                    alt="AI 생성 표지"
                    style={{
                    width: '100%',
                    maxHeight: 400,
                    objectFit: 'cover',
                    borderRadius: 8,
                    }}
                />
                </Stack>
            )}
            </DialogContent>
            <DialogActions>
            <Button onClick={handleModalClose} color="primary" variant="contained">
                확인
            </Button>
            <Button onClick={handleRetry} color="secondary">
                재시도
            </Button>
            </DialogActions>
        </Dialog>
        </Container>
    );
};

export default ManuscriptEditor;