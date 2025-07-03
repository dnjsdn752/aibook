import axios from "axios";

// 사용자 ID로 대여한 책 목록 조회
export const getUserReadings = async (userId: number) => {
  const res = await axios.get(`/reading/user/${userId}`);
  return res.data; // [{ id, bookId, ... }]
};
