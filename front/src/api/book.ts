import axios from "axios";

// bookId로 도서 상세 정보 조회
export const getBookDetail = async (bookId: number) => {
  const res = await axios.get(`/books/${bookId}`);
  return res.data; // { title, authorName, content, aiImage }
};
