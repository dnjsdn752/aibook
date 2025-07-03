// 대여한 책 목록 API 함수

export const getBorrowedBooks = async (userId: number) => {
  const readings = await fetch(`/readings/user/${userId}`).then((res) => res.json());
  const res = await fetch(`/readings/user/${userId}`);
  const text = await res.text();
  const data = text ? JSON.parse(text) : [];

  const bookDetails = await Promise.all(
    readings.map(async (r: any) => {
      const book = await fetch(`/books/${r.bookId}`).then((res) => res.json());
      const author = await fetch(`/authors/${book.authorId}`).then((res) => res.json());
      const ai = await fetch(`/ais/${r.bookId}`).then((res) => res.json());

      return {
        bookId: r.bookId,
        title: book.title,
        authorName: author.name,
        aiImage: ai.imageUrl, // 백엔드 필드에 따라 다를 수 있음
      };
    })
  );

  return bookDetails;
};
