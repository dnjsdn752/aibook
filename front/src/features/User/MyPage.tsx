import React from 'react';
import { Container, Typography, Stack } from '@mui/material';
import BuySubscriptionButton from './BuySubscription'; 
import BuyPointButton from './BuyPoint'; 

const MyPage: React.FC = () => {
  const userId = 1; // 예시용 ID (실제로는 로그인 상태에서 받아와야 함)
  const amount = 3000;

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Typography variant="h4" gutterBottom>
        마이페이지
      </Typography>

      <Stack spacing={3} mt={4}>
        {/* 구독권 구매 버튼 */}
        <BuySubscriptionButton userId={userId} />

        {/* 포인트 구매 버튼 */}
        <BuyPointButton userId={userId} />
      </Stack>
    </Container>
  );
};

export default MyPage;
