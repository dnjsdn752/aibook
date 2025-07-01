import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8086", // 도서관리 API
});

export const fetchBooks = async (keyword = "") => {
  const res = await api.get("/books", {
    params: keyword ? { title: keyword } : {},
  });
  return res.data._embedded?.books || [];
};
