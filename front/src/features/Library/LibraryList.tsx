import React, { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getBooks, applyReading } from "../../api/library";

export const LibraryList: React.FC = () => {
  const [search, setSearch] = useState("");
  const [selectedBook, setSelectedBook] = useState<any | null>(null);
  const userId = Number(localStorage.getItem("userId"));


  const { data = [], isLoading, error } = useQuery({
    queryKey: ["books"],
    queryFn: () => getBooks(),
  });

  const extractIdFromHref = (href: string): number => {
  const parts = href.split("/");
  return parseInt(parts[parts.length - 1], 10);
};


  const handleRent = async (book: any) => {
  
  const bookHref = book._links?.self?.href;

  if (!bookHref || isNaN(userId)) {
    alert("유효한 정보가 없습니다.");
    return;
  }

  const bookId = extractIdFromHref(bookHref);
  console.log("✅ 대여 요청 payload:", { userId, bookId });
  try {
    await applyReading({
      userId,        
      bookId 
    });

    alert(`"${book.title}" 도서를 대여했습니다.`);
    setSelectedBook(null);
  } catch (error) {
    console.error("대여 실패", error);
    alert("도서 대여에 실패했습니다.");
  }
};

    

  if (isLoading) return <p>로딩 중...</p>;
  if (error) return <p>에러가 발생했습니다.</p>;

  const keyword = search.toLowerCase();
  const filteredBooks = data.filter(
    (book: any) =>
      book.title?.toLowerCase().includes(keyword) ||
      book.authorName?.toLowerCase().includes(keyword)
  );

  return (
    <div
      style={{
        minHeight: "100vh",
        background: "linear-gradient(to right top, #f5f7fa, #c3cfe2)",
        padding: "2rem",
        boxSizing: "border-box",
      }}
    >
      <div style={{ maxWidth: "1200px", margin: "0 auto" }}>
        {/* 검색창 */}
        <div
          style={{
            marginBottom: "2rem",
            background: "rgba(255, 255, 255, 0.7)",
            backdropFilter: "blur(10px)",
            borderRadius: "12px",
            padding: "1rem",
            boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
            display: "flex",
            justifyContent: "center",
          }}
        >
          <input
            type="text"
            placeholder="책 제목 또는 작가 검색"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            style={{
              padding: "0.75rem 1rem",
              width: "100%",
              maxWidth: "1000px",
              borderRadius: "8px",
              border: "1px solid #ccc",
              fontSize: "1rem",
              background: "#fff",
              boxShadow: "inset 0 1px 3px rgba(0,0,0,0.1)",
            }}
          />
        </div>

        {/* 카드 리스트 */}
        <div
          style={{
            display: "grid",
            gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
            gap: "1.5rem",
          }}
        >
          {filteredBooks.map((book: any) => (
            <div
              key={book._links.self.href}
              onClick={() => setSelectedBook(book)}
              style={{
                background: "rgba(255, 255, 255, 0.9)",
                borderRadius: "12px",
                boxShadow: "0 4px 16px rgba(0,0,0,0.1)",
                overflow: "hidden",
                transition: "transform 0.2s ease, box-shadow 0.2s ease",
                cursor: "pointer",
              }}
              onMouseEnter={(e) => {
                (e.currentTarget as HTMLDivElement).style.transform = "scale(1.03)";
                (e.currentTarget as HTMLDivElement).style.boxShadow =
                  "0 8px 20px rgba(0,0,0,0.15)";
              }}
              onMouseLeave={(e) => {
                (e.currentTarget as HTMLDivElement).style.transform = "scale(1)";
                (e.currentTarget as HTMLDivElement).style.boxShadow =
                  "0 4px 16px rgba(0,0,0,0.1)";
              }}
            >
              <img
                src={
                  book.aiImage && book.aiImage.trim() !== ""
                    ? book.aiImage
                    : "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?fit=crop&w=500&q=80"
                }
                alt={book.title}
                style={{
                  width: "100%",
                  height: "250px",
                  objectFit: "cover",
                }}
              />
              <div style={{ padding: "1rem" }}>
                <h3
                  style={{
                    margin: "0 0 0.5rem",
                    fontSize: "1.1rem",
                    color: "#333",
                  }}
                >
                  {book.title}
                </h3>
                <p
                  style={{
                    margin: 0,
                    fontSize: "0.95rem",
                    color: "#666",
                  }}
                >
                  {book.authorName}
                </p>

                {book.isBestSeller === true && (
                  <div
                    style={{
                      backgroundColor: "#ffcc00",
                      color: "#333",
                      fontWeight: "bold",
                      borderRadius: "8px",
                      padding: "0.25rem 0.5rem",
                      display: "inline-block",
                      marginTop: "0.5rem",
                      fontSize: "0.85rem",
                    }}
                  >
                    🏆 베스트셀러
                  </div>
                )}
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* 모달 */}
      {selectedBook && (
        <div
          style={{
            position: "fixed",
            top: 0,
            left: 0,
            width: "100vw",
            height: "100vh",
            backgroundColor: "rgba(0,0,0,0.5)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            zIndex: 1000,
          }}
          onClick={() => setSelectedBook(null)}
        >
          <div
            style={{
              background: "white",
              padding: "2rem",
              borderRadius: "12px",
              maxWidth: "600px",
              width: "90%",
              position: "relative",
            }}
            onClick={(e) => e.stopPropagation()}
          >
            <button
              onClick={() => setSelectedBook(null)}
              style={{
                position: "absolute",
                top: "1rem",
                right: "1rem",
                background: "transparent",
                border: "none",
                fontSize: "1.2rem",
                cursor: "pointer",
              }}
            >
              ✖
            </button>

            <img
              src={
                selectedBook.aiImage && selectedBook.aiImage.trim() !== ""
                  ? selectedBook.aiImage
                  : "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?fit=crop&w=500&q=80"
              }
              alt={selectedBook.title}
              style={{
                width: "100%",
                height: "300px",
                objectFit: "cover",
                borderRadius: "8px",
              }}
            />
            <h2 style={{ marginTop: "1rem" }}>{selectedBook.title}</h2>
            <h4>{selectedBook.authorName}</h4>

            <p style={{ marginTop: "1rem", fontWeight: "bold" }}>📘 AI 요약</p>
            <p style={{ color: "#333", whiteSpace: "pre-wrap" }}>
              {selectedBook.aiSummary || "AI 요약이 없습니다."}
            </p>

            <button
              onClick={() => handleRent(selectedBook)}
              style={{
                marginTop: "1.5rem",
                padding: "0.75rem 1.5rem",
                backgroundColor: "#4CAF50",
                color: "white",
                border: "none",
                borderRadius: "8px",
                fontSize: "1rem",
                cursor: "pointer",
              }}
            >
              📚 대여하기
            </button>
          </div>
        </div>
      )}
    </div>
  );
};
