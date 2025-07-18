import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

// 원고 등록
export const registerManuscript = async (data: {
  title: string;
  content: string;
  authorId: number;
}) => {
  return await api.post("/manuscripts/registermanuscript", data);
};

// 원고 수정
export const editManuscript = async (id: number, data: {
  title: string;
  content: string;
  authorId: number;
}) => {
  return await api.put(`/manuscripts/${id}/editmanuscript`, data);
};

// AI 생성 요청
export const requestAi = async (id: number) => {
  return await api.put(`/manuscripts/${id}/requestai`);
};

// 출간 요청
export const requestPublishing = async (id: number) => {
  return await api.put(`/manuscripts/${id}/requestpublishing`);
};

// 원고 단건 조회
export const getManuscript = async (id: number) => {
  return await api.get(`/manuscripts/${id}`);
};

// 해당 작가의 출간되지 않은 원고 목록
export const getMyManuscripts = async (authorId: number) => {
  const response = await api.get(`/manuscripts/author/${authorId}`);
  return response.data;
};
