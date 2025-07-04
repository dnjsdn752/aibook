// src/api/library.ts

import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

// books 리스트 가져오기
export const getBooks = async (keyword = "") => {
  const res = await api.get("/manuscripts", {
    params: keyword ? { title: keyword } : {},
  });
  // HAL 응답에서 배열만 추출
  return res.data._embedded?.manuscripts || [];
};

export interface ReadingApplyRequest {
  userId: number;
  bookId: number;
}

export const applyReading = async (data: ReadingApplyRequest) => {
  const response = await api.post("/reading", data, {
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response.data;
};

