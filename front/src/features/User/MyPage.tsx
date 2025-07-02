import React from 'react';
import { Container, Typography, Stack } from '@mui/material';
import BuySubscriptionButton from './BuySubscription'; 

const MyPage: React.FC = () => {
  const userId = 1; // 예시용 ID (실제론 로그인 정보에서 받아와야 함)

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Typography variant="h4" gutterBottom>
        마이페이지
      </Typography>

      <Stack spacing={3} mt={4}>
        <BuySubscriptionButton userId={userId} />
      </Stack>
    </Container>
  );
};


export default MyPage;