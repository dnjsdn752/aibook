// src/api/manuscript.ts
// src/api/manuscript.ts
import axios from "axios";

const api = axios.create({
  baseURL: "https://8088-dnjsdn752-aibook-n7lzoljuat8.ws-us120.gitpod.io", // Gateway 주소!
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


// 해당 작가의 출간되지 않은 원고 목록
export const getMyManuscripts = async (authorId: number) => {
    const response = await axios.get(`/manuscripts/author/${authorId}`, {
        params: {
        authorId,
        status: false,  // 미출간 상태만 가져오는 필터
        },
    });
    return response.data;  // 서버에서 배열(JSON 리스트) 반환한다고 가정
};