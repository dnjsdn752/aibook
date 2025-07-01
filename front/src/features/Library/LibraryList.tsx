import React, { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getBooks } from "../../api/library";

export const LibraryList: React.FC = () => {
  const [search, setSearch] = useState("");

  const { data = [], isLoading, error } = useQuery({
    queryKey: ["books"],
    queryFn: () => getBooks(),
  });

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
      <div
        style={{
          maxWidth: "1200px",
          margin: "0 auto",
        }}
      >
        {/* 상단 네비게이션 */}
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            marginBottom: "2rem",
            padding: "1rem",
            borderRadius: "8px",
            background: "#fff",
            boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
          }}
        >
          <h1
            style={{
              margin: 0,
              fontSize: "1.5rem",
              color: "#333",
            }}
          >
            도서관리 시스템
          </h1>
          <button
            style={{
              backgroundColor: "#4a90e2",
              color: "#fff",
              border: "none",
              borderRadius: "6px",
              padding: "0.5rem 1rem",
              fontSize: "1rem",
              cursor: "pointer",
              transition: "background-color 0.2s ease",
            }}
            onClick={() => {
              // 마이페이지 라우팅
              window.location.href = "/mypage";
            }}
            onMouseEnter={(e) =>
              ((e.currentTarget as HTMLButtonElement).style.backgroundColor =
                "#357ABD")
            }
            onMouseLeave={(e) =>
              ((e.currentTarget as HTMLButtonElement).style.backgroundColor =
                "#4a90e2")
            }
          >
            마이페이지
          </button>
        </div>

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
            justifyContent: "center",  // 중앙정렬
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
            maxWidth: "1000px",      // 폭 제한
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
                  book.aiImage ||
                  "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?fit=crop&w=500&q=80"
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
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
