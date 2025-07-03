import React, { useState } from 'react';
import { Button, TextField, Snackbar, Alert, Stack } from '@mui/material';
import { buyPoint } from '../../api/user_buy';

interface Props {
  userId: number;
}

const BuyPointButton: React.FC<Props> = ({ userId }) => {
  const [amount, setAmount] = useState<number>(0); 
  const [open, setOpen] = useState(false);
  const [error, setError] = useState(false);
  

  const handleBuy = async () => {
    try {
      await buyPoint({ userId, amount }); 
      setError(false);
    } catch (err) {
      console.error('포인트 충전 실패', err);
      setError(true);
    } finally {
      setOpen(true);
    }
  };

  return (
    <>
      <Stack spacing={2}>
        <TextField
          label="포인트 금액"
          type="number"
          value={amount}
          onChange={(e) => setAmount(parseInt(e.target.value))}
        />
        <Button variant="contained" color="primary" onClick={handleBuy}>
          💰 포인트 구매
        </Button>
      </Stack>

      <Snackbar open={open} autoHideDuration={3000} onClose={() => setOpen(false)}>
        <Alert severity={error ? 'error' : 'success'} onClose={() => setOpen(false)}>
          {error ? '포인트 충전 실패 😢' : '포인트 충전 완료 🎉'}
        </Alert>
      </Snackbar>
    </>
  );
};

export default BuyPointButton;
