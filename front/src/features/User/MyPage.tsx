import React, { useEffect, useState } from 'react';
import {
  Container,
  Typography,
  Stack,
  Box,
  Divider,
  Paper,
  Button,
  CircularProgress,
} from '@mui/material';
import BuySubscriptionButton from './BuySubscription';
import BuyPointButton from './BuyPoint';
import { myData } from "../../api/user";
import { myPoint } from "../../api/user";
import { myReading } from "../../api/user";
import { useNavigate } from 'react-router-dom'; // 도서 상세 페이지 이동을 위해 사용

interface UserInfo {
  email: string;
  name: string;
  point: number;
  hasSubscription: boolean;
}

interface RentedBook {
  id: number;
  title: string;
  author: string;
}

const MyPage: React.FC = () => {
  const userId = 1; // 예시용 ID
  const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
  const [rentedBooks, setRentedBooks] = useState<RentedBook[]>([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate(); // 페이지 이동 함수
  useEffect(() => {
    const fetchUserData = async () => {
      setLoading(true);
      try {
        const response = await myData(userId); // API 요청
        const point = await myPoint(userId);
        const reading = await myPoint(userId);
      setUserInfo({
        email: response.data.email,
        name: response.data.userName,
        point: point.data.point,
        hasSubscription: response.data.isSubscription, // 또는 user.hasSubscription
      });
      setRentedBooks([
        //reading.data
      ]);

      } catch (error) {
      console.error('유저 정보 로드 실패:', error);
      }
      
      setLoading(false);
    };

    fetchUserData();
  }, []);

  if (loading || !userInfo) {
    return (
      <Container maxWidth="sm" sx={{ mt: 4 }}>
        <CircularProgress />
      </Container>
    );
  }

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Typography variant="h4" gutterBottom>
        마이페이지
      </Typography>

      {/* ✅ 개인정보 카드 */}
      <Paper elevation={3} sx={{ p: 3, mt: 4 }}>
        <Typography variant="h6" gutterBottom>
          개인정보
        </Typography>
        <Typography>👤 이름: {userInfo.name}</Typography>
        <Typography>📧 이메일: {userInfo.email}</Typography>
        <Typography>💰 포인트: {userInfo.point.toLocaleString()}P</Typography>
        <Typography>
          🎫 구독권: {userInfo.hasSubscription ? '구매함' : '미구매'}
        </Typography>
      </Paper>

      <Divider sx={{ my: 4 }} />

      <Stack spacing={3}>
        {/* ✅ 구독 및 포인트 구매 버튼 */}
        <BuySubscriptionButton userId={userId} />
        <BuyPointButton userId={userId} />
      </Stack>

      <Divider sx={{ my: 4 }} />

      {/* ✅ 대여한 도서 목록 */}
      <Box>
        <Typography variant="h6" gutterBottom>
          📚 대여한 도서 목록
        </Typography>

        {rentedBooks.length > 0 ? (
          <Stack spacing={2}>
            {rentedBooks.map((book) => (
              <Button
                key={book.id}
                variant="outlined"
                fullWidth
                onClick={() => navigate(`/books/${book.id}`)} // 도서 상세 페이지로 이동
                sx={{
                  justifyContent: 'space-between',
                  textAlign: 'left',
                  p: 2,
                }}
              >
                <Box>
                  <Typography variant="subtitle1">{book.title}</Typography>
                  <Typography variant="body2" color="text.secondary">
                    저자: {book.author}
                  </Typography>
                </Box>
              </Button>
            ))}
          </Stack>
        ) : (
          <Typography>대여한 도서가 없습니다.</Typography>
        )}
      </Box>
    </Container>
  );
};

export default MyPage;
