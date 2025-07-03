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
import { myData, myPoint } from "../../api/user";
import { getUserReadings } from "../../api/reading";
import { getBookDetail } from "../../api/book";
import { getAiInfo } from "../../api/ai";
import { useNavigate } from 'react-router-dom';

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
  content: string;
  image: string;
}

const MyPage: React.FC = () => {
  const userId = 1; // 로그인 사용자 ID (임시)
  const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
  const [rentedBooks, setRentedBooks] = useState<RentedBook[]>([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserData = async () => {
      setLoading(true);
      try {
        const response = await myData(userId);
        const point = await myPoint(userId);

        setUserInfo({
          email: response.data.email,
          name: response.data.userName,
          point: point.data.point,
          hasSubscription: response.data.isSubscription,
        });

        const readingList = await getUserReadings(userId);
        const books = await Promise.all(
          readingList.map(async (reading: any) => {
            const book = await getBookDetail(reading.bookId);
            const ai = await getAiInfo(reading.bookId);
            return {
              id: book.id,
              title: book.title,
              author: book.authorName,
              content: ai.content,
              image: ai.imageUrl,
            };
          })
        );

        setRentedBooks(books);
      } catch (error) {
        console.error('유저 정보 또는 도서 정보 로드 실패:', error);
      } finally {
        setLoading(false);
      }
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

      {/* ✅ 개인정보 */}
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

      {/* ✅ 구독 / 포인트 구매 버튼 */}
      <Stack spacing={3}>
        <BuySubscriptionButton userId={userId} />
        <BuyPointButton userId={userId} />
      </Stack>

      <Divider sx={{ my: 4 }} />

      {/* ✅ 기존 버튼 목록 - 도서 상세 페이지 이동용 */}
      <Box>
        <Typography variant="h6" gutterBottom>
          📚 대여한 도서 목록 (요약)
        </Typography>

        {rentedBooks.length > 0 ? (
          <Stack spacing={2}>
            {rentedBooks.map((book) => (
              <Button
                key={book.id}
                variant="outlined"
                fullWidth
                onClick={() => navigate(`/books/${book.id}`)}
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

      <Divider sx={{ my: 4 }} />

      {/* ✅ 대여한 도서 상세 정보 (제목, 저자, 이미지, 내용) */}
      <Box>
        <Typography variant="h6" gutterBottom>
          🧠 대여한 도서 상세 정보
        </Typography>

        {rentedBooks.length > 0 ? (
          rentedBooks.map((book) => (
            <Box key={book.id} sx={{ mt: 3 }}>
              <Typography variant="subtitle1" fontWeight="bold">
                📖 제목: {book.title}
              </Typography>
              <Typography variant="body1">✍️ 저자: {book.author}</Typography>
              {book.image && (
                <Box
                  component="img"
                  src={book.image}
                  alt={`${book.title} 표지`}
                  sx={{ width: '100%', maxHeight: 300, my: 2, borderRadius: 2 }}
                />
              )}
              <Typography variant="body2" sx={{ whiteSpace: 'pre-wrap' }}>
                {book.content}
              </Typography>
              <Divider sx={{ my: 4 }} />
            </Box>
          ))
        ) : (
          <Typography>📭 대여한 도서가 없습니다.</Typography>
        )}
      </Box>
    </Container>
  );
};

export default MyPage;
