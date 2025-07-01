import React, { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { fetchBooks } from "../../api/library";

export const LibraryList: React.FC = () => {
  const [search, setSearch] = useState("");

  const { data = [], isLoading, error } = useQuery({
    queryKey: ["books"],
    queryFn: () => fetchBooks(search),
  });

  if (isLoading) return <p>로딩 중...</p>;
  if (error) return <p>에러가 발생했습니다.</p>;

  return (
    <div>
      <input
        type="text"
        placeholder="책 제목 검색"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{ padding: "0.5rem", marginBottom: "1rem", width: "100%" }}
      />
      <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
        {data
          .filter((book: any) =>
            book.title.toLowerCase().includes(search.toLowerCase())
          )
          .map((book: any) => (
            <div
              key={book.id}
              style={{
                border: "1px solid #ddd",
                padding: "1rem",
                width: "200px",
              }}
            >
              <img
                src={book.coverUrl}
                alt={book.title}
                style={{ width: "100%", height: "auto" }}
              />
              <h3>{book.title}</h3>
            </div>
          ))}
      </div>
    </div>
  );
};
