// front/src/api/admin.ts
import axios from "axios";

const api = axios.create({
  baseURL: "https://super-duper-space-xylophone-x4gxwvq7xrwf69gx-8088.app.github.dev/",
});

// ❌ 조회 API가 현재 없음 — 임시 Mock 또는 백엔드에 GET /authors 요청 추가 필요
export const fetchAuthorRequests = async (): Promise<any[]> => {
  const res = await api.get("/authors");

  const authors = res.data._embedded?.authors || [];

  // 각 작가 객체에 id를 추출해서 추가
  return authors.map((author: any) => {
    const id = author._links?.self?.href?.split("/")?.pop();
    return { ...author, id };
  });
};

export const approveAuthor = async (id: number | string): Promise<void> => {
  await api.put(`/authors/${id}/approveauthor`);
};

export const rejectAuthor = async (id: number | string): Promise<void> => {
  await api.put(`/authors/${id}/disapproveauthor`);
};