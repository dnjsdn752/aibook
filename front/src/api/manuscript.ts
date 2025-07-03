// src/api/manuscript.ts
// src/api/manuscript.ts
import axios from "axios";

const api = axios.create({
  baseURL: "https://expert-parakeet-g4rx999gq7w5h97r-8088.app.github.dev", // Gateway 주소!
});

// 원고 등록
export const registerManuscript = async (data: {
    title: string;
    content: string;
}) => {
    return await api.post("/manuscripts/registermanuscript", data);
};

// 원고 수정
export const editManuscript = async (id: number, data: {
    title: string;
    content: string;
}) => {
    return await api.put(`/manuscripts/${id}/editmanuscript`, data);
};

// AI 생성 요청 (LLM 기반)
export const requestAi = async (id: number) => {
    return await api.put(`/manuscripts/${id}/requestai`);
};


// 출간 요청
export const requestPublishing = async (id: number) => {
    return await api.put(`/manuscripts/${id}/requestpublishing`);
};

// 원고 단건 조회 (AI 결과 포함)
export const getManuscript = async (id: number) => {
    return await api.get(`/manuscripts/${id}`);
};
