import React from "react";
import { Link } from "react-router-dom";

export const Layout: React.FC<{ children: React.ReactNode }> = ({ children }) => (
  <div>
    <header style={{ display: "flex", justifyContent: "space-between", padding: "1rem", background: "#f5f5f5" }}>
      <h1>도서 관리 시스템</h1>
      <nav>
        <Link to="/mypage">마이페이지</Link>
      </nav>
    </header>
    <main style={{ padding: "1rem" }}>
      {children}
    </main>
  </div>
);
