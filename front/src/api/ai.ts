import axios from "axios";

// bookId로 AI 정보 조회 (이미지, 요약 등)
export const getAiInfo = async (bookId: number) => {
  try {
    const res = await axios.get(`/ais/${bookId}`);
    return res.data; // { imageUrl, content }
  } catch {
    return { imageUrl: null, content: null }; // AI가 없을 경우 예외 처리
  }
};
