import React, { useEffect, useState } from 'react';
import { getMyManuscripts } from '../../api/manuscript';
import {
    List,
    ListItem,
    ListItemText,
    ListItemButton,
    Typography,
    Button,
    Stack,
    CircularProgress,
    Container,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';

const TEMP_AUTHOR_ID = 1; // ✅ 하드코딩한 작가 ID

const MyDraftList: React.FC = () => {
    const [manuscripts, setManuscripts] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchManuscripts = async () => {
            try {
                const res = await getMyManuscripts(TEMP_AUTHOR_ID);
                console.log('API 응답:', res);

                //const manuscripts = res._embedded?.manuscripts || [];
                setManuscripts(res); 
            } catch (err) {
                console.error('원고 불러오기 실패:', err);
            } finally {
                setLoading(false);
            }
        };
        fetchManuscripts();
    }, []);

    const handleSelect = (id: number) => {
        navigate(`/manuscripts/editor/${id}`); // 👉 이 경로로 이동해서 해당 원고 불러오도록 구현
    };

    const handleCreateNew = () => {
        navigate('/manuscripts/editor'); // 새 원고 작성
    };

    return (
        <Container maxWidth="sm" sx={{ mt: 4 }}>
        <Typography variant="h5" gutterBottom>
            ✍️ 내 미출간 원고
        </Typography>

        {loading ? (
            <CircularProgress />
        ) : (
            <>
            <List>
                {manuscripts.map((item: any) => (
                <ListItem key={item.id}>
                    <ListItemButton onClick={() => handleSelect(item.id)}>
                        <ListItemText
                        primary={item.title || '(제목 없음)'}
                        secondary={`ID: ${item.id}`}
                        />
                    </ListItemButton>
                </ListItem>
                ))}
            </List>

            <Stack direction="row" justifyContent="flex-end" mt={2}>
                <Button variant="contained" onClick={handleCreateNew}>
                ➕ 새 원고 작성
                </Button>
            </Stack>
            </>
        )}
        </Container>
    );
};

export default MyDraftList;
