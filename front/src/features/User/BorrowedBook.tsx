// src/features/User/BorrowedBook.tsx
import React, { useEffect, useState } from "react";
import { getUserReadings } from "../../api/reading";
import { getBookDetail } from "../../api/book";
import { getAiInfo } from "../../api/ai";
import {
  Typography,
  Box,
  Card,
  CardContent,
  CardMedia,
} from "@mui/material";

interface BookInfo {
  id: number;
  title: string;
  author: string;
  content: string;
  image?: string;
}

const BorrowedBook: React.FC<{ userId: number }> = ({ userId }) => {
  const [books, setBooks] = useState<BookInfo[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const readingList = await getUserReadings(userId);
        const bookList = await Promise.all(
          readingList.map(async (r: any) => {
            const book = await getBookDetail(r.bookId);
            const ai = await getAiInfo(r.bookId);
            return {
              id: r.bookId,
              title: book.title,
              author: book.authorName,
              content: book.content,
              image: ai.imageUrl,
            };
          })
        );
        setBooks(bookList);
      } catch (error) {
        console.error("📕 책 정보를 불러오는 중 오류:", error);
      }
    };

    fetchData();
  }, [userId]);

  return (
    <Box>
      <Typography variant="h6" gutterBottom>
        📚 대여한 도서 목록
      </Typography>
      {books.length === 0 ? (
        <Typography>대여한 도서가 없습니다.</Typography>
      ) : (
        books.map((book) => (
          <Card key={book.id} sx={{ mb: 3 }}>
            {book.image && (
              <CardMedia
                component="img"
                height="200"
                image={book.image}
                alt={`${book.title} 표지`}
              />
            )}
            <CardContent>
              <Typography variant="h6">{book.title}</Typography>
              <Typography variant="subtitle2" color="text.secondary">
                저자: {book.author}
              </Typography>
              <Typography variant="body2" mt={1}>
                {book.content}
              </Typography>
            </CardContent>
          </Card>
        ))
      )}
    </Box>
  );
};

export default BorrowedBook;
