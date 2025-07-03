import React, { useEffect, useState } from "react";
import { Typography, Box, Card, CardMedia, CardContent } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { getBorrowedBooks } from "../../api/mypage";

interface Props {
  userId: number;
}

interface Book {
  bookId: number;
  title: string;
  authorName: string;
  aiImage: string;
}

const BorrowedBookList: React.FC<Props> = ({ userId }) => {
  const [books, setBooks] = useState<Book[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetch = async () => {
      const result = await getBorrowedBooks(userId);
      setBooks(result);
    };
    fetch();
  }, [userId]);

  return (
    <>
      <Typography variant="h5" mt={5} gutterBottom>
        대여한 책 목록
      </Typography>

      <Box sx={{ display: "flex", flexWrap: "wrap", gap: 2 }}>
        {books.length === 0 ? (
          <Typography variant="body2">대여한 책이 없습니다.</Typography>
        ) : (
          books.map((book) => (
            <Card
              key={book.bookId}
              sx={{ width: 150, cursor: "pointer" }}
              onClick={() => navigate(`/book/${book.bookId}`)}
            >
              <CardMedia
                component="img"
                height="180"
                src={book.aiImage}
                alt={book.title}
              />
              <CardContent>
                <Typography variant="subtitle1" noWrap>
                  {book.title}
                </Typography>
              </CardContent>
            </Card>
          ))
        )}
      </Box>
    </>
  );
};

export default BorrowedBookList;
