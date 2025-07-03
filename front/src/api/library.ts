// src/api/library.ts

import axios from "axios";

const api = axios.create({
  baseURL: "https://8088-dnjsdn752-aibook-kcc5uifn888.ws-us120.gitpod.io", // 도서관리 서비스 포트
});

// books 리스트 가져오기
export const getBooks = async (keyword = "") => {
  const res = await api.get("/manuscripts", {
    params: keyword ? { title: keyword } : {},
  });
  // HAL 응답에서 배열만 추출
  return res.data._embedded?.manuscripts || [];
};
