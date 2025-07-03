import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

interface BookInfo {
  title: string;
  authorName: string;
  content: string;
  aiImage: string;
}

const BookDetail: React.FC = () => {
  const { bookId } = useParams();
  const [book, setBook] = useState<BookInfo | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const bookRes = await fetch(`/books/${bookId}`).then((res) => res.json());
        const authorRes = await fetch(`/authors/${bookRes.authorId}`).then((res) => res.json());
        const aiRes = await fetch(`/ais/book/${bookId}`).then((res) => res.json());

        setBook({
          title: bookRes.title,
          authorName: authorRes.name,
          content: aiRes.summary || aiRes.content || "내용 없음",
          aiImage: aiRes.imageUrl || aiRes.aiImage || "", // 백엔드에서 설정한 필드명에 따라 조정
        });
      } catch (error) {
        console.error("Error loading book detail:", error);
      }
    };

    fetchData();
  }, [bookId]);

  if (!book) return <div>불러오는 중...</div>;

  return (
    <div style={{ padding: "1rem" }}>
      <h2>{book.title}</h2>
      <h4>저자: {book.authorName}</h4>
      <img src={book.aiImage} alt="AI 표지" style={{ width: "300px", marginBottom: "1rem" }} />
      <p>{book.content}</p>
    </div>
  );
};

export default BookDetail;
