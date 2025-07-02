import React, { useState } from 'react';
import { Button, Snackbar, Alert } from '@mui/material';
import { buySubscription } from '../../api/user_buy'; 

interface Props {
  userId: number; // 마이페이지에서 로그인된 사용자 ID 전달
}

const BuySubscriptionButton: React.FC<Props> = ({ userId }) => {
  const [open, setOpen] = useState(false);
  const [error, setError] = useState(false);

  const handleBuy = async () => {
    try {
      console.log("요청할 데이터:", { isSubscription: true });
      await buySubscription(userId, { isSubscription: true }); // API 호출
      setError(false); // 성공
    } catch (err) {
      console.error('구독 실패', err);
      setError(true); // 실패
    } finally {
      setOpen(true); // 성공/실패와 관계없이 Snackbar 열기
    }
  };

  return (
    <>
      <Button variant="contained" color="primary" onClick={handleBuy}>
        🪪 구독권 구매
      </Button>

      <Snackbar open={open} autoHideDuration={3000} onClose={() => setOpen(false)}>
        <Alert severity={error ? 'error' : 'success'} onClose={() => setOpen(false)}>
          {error ? '구독 실패 😢' : '구독 완료 🎉'}
        </Alert>
      </Snackbar>
    </>
  );
};

export default BuySubscriptionButton;