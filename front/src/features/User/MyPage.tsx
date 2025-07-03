import React from 'react';
import { Container, Typography, Stack } from '@mui/material';
import BuySubscriptionButton from './BuySubscription';
import BuyPointButton from './BuyPoint';
import BorrowedBookList from './BorrowedBookList'; // ✅ 대여 목록 UI 추가

const MyPage: React.FC = () => {
  const userId = 1; // 예시용 ID

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      {/* 🔼 기존 상단 내용 그대로 유지 */}
      <Typography variant="h4" gutterBottom>
        마이페이지
      </Typography>

      <Typography variant="body1">
        여기에 회원 정보나 구독 정보가 표시됩니다.
      </Typography>

      <Stack spacing={3} mt={4}>
        <BuySubscriptionButton userId={userId} />
        <BuyPointButton userId={userId} />
      </Stack>

      {/* 🔽 아래에 대여한 책 목록 추가 */}
      <BorrowedBookList userId={userId} />
    </Container>
  );
};

export default MyPage;
