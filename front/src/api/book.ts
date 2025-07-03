// 📁 src/api/book.ts
export const getBookDetail = async (bookId: string) => {
  const bookRes = await fetch(`/books/${bookId}`);
  const book = await bookRes.json();

  const authorRes = await fetch(`/authors/${book.authorId}`);
  const author = await authorRes.json();

  const aiRes = await fetch(`/ais/${bookId}`);
  const ai = await aiRes.json();

  return {
    bookId,
    title: book.title,
    authorName: author.name,
    content: ai.summary,
    aiImage: ai.imageUrl,
  };
};

