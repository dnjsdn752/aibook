// front/src/Admin/adminApi.ts
import axios from "axios";

const api = axios.create({
  baseURL: "https://super-duper-space-xylophone-x4gxwvq7xrwf69gx-8088.app.github.dev/", // ✅ 실제 게이트웨이 주소
});

// 작가 신청 목록 조회
export const fetchAuthorRequests = async (): Promise<any[]> => {
  const res = await api.get("/author-requests");
  return res.data; // 서버가 배열 형태로 작가 신청 목록 반환한다고 가정
};

// 작가 신청 승인
export const approveAuthor = async (id: number | string): Promise<void> => {
  await api.put(`/author-requests/${id}/approve`);
};

// 작가 신청 거절
export const rejectAuthor = async (id: number | string): Promise<void> => {
  await api.put(`/author-requests/${id}/reject`);
};