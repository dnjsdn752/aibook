// front/src/api/admin.ts
import axios from "axios";

const api = axios.create({
  baseURL: "https://super-duper-space-xylophone-x4gxwvq7xrwf69gx-8088.app.github.dev/",
});

// ❌ 조회 API가 현재 없음 — 임시 Mock 또는 백엔드에 GET /authors 요청 추가 필요
export const fetchAuthorRequests = async (): Promise<any[]> => {
  const res = await api.get("/authors"); // ✅ 만약 GET /authors가 있다면 이렇게 사용
  return res.data._embedded?.authors || [];
};

export const approveAuthor = async (id: number | string): Promise<void> => {
  await api.put(`/authors/${id}/approveauthor`);
};

export const rejectAuthor = async (id: number | string): Promise<void> => {
  await api.put(`/authors/${id}/disapproveauthor`);
};