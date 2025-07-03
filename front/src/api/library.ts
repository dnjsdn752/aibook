// src/api/library.ts

import axios from "axios";

const api = axios.create({
  baseURL: "https://fluffy-spork-6jjrxqwjr9f54qw-8088.app.github.dev/", // 도서관리 서비스 포트
});

// books 리스트 가져오기
export const getBooks = async (keyword = "") => {
  const res = await api.get("/books", {
    params: keyword ? { title: keyword } : {},
  });
  // HAL 응답에서 배열만 추출
  return res.data._embedded?.books || [];
};
