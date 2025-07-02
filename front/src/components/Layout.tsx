import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";

interface LayoutProps {
  isAuthor?: boolean;
}

export const Layout: React.FC<LayoutProps> = ({ isAuthor }) => {
  const navigate = useNavigate();
  const isLoggedIn = Boolean(localStorage.getItem("token"));

  const handleLogout = () => {
    localStorage.removeItem("token");
    alert("로그아웃 되었습니다.");
    navigate("/login");
  };

  return (
    <div
      style={{
        maxWidth: "1200px",
        margin: "0 auto",
        padding: "1rem",
      }}
    >
      {/* 상단 네비게이션 */}
      <header
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          marginBottom: "2rem",
        }}
      >
        <Link
          to="/"
          style={{
            textDecoration: "none",
            color: "#333",
            fontSize: "1.5rem",
            fontWeight: "bold",
            cursor: "pointer",
          }}
        >
          도서관리 시스템
        </Link>

        <div style={{ display: "flex", gap: "0.5rem" }}>
          {/* 로그인 + 작가일 때 도서등록 */}
          {isLoggedIn && isAuthor && (
            <Link to="/manuscripts/new">
              <button
                style={{
                  backgroundColor: "#4a90e2",
                  color: "#fff",
                  border: "none",
                  borderRadius: "6px",
                  padding: "0.5rem 1rem",
                  fontSize: "1rem",
                  cursor: "pointer",
                }}
              >
                도서 등록
              </button>
            </Link>
          )}

          {isLoggedIn ? (
            <>
              <Link to="/mypage">
                <button
                  style={{
                    backgroundColor: "#4a90e2",
                    color: "#fff",
                    border: "none",
                    borderRadius: "6px",
                    padding: "0.5rem 1rem",
                    fontSize: "1rem",
                    cursor: "pointer",
                  }}
                >
                  마이페이지
                </button>
              </Link>
              <button
                onClick={handleLogout}
                style={{
                  backgroundColor: "#d9534f",
                  color: "#fff",
                  border: "none",
                  borderRadius: "6px",
                  padding: "0.5rem 1rem",
                  fontSize: "1rem",
                  cursor: "pointer",
                }}
              >
                로그아웃
              </button>
            </>
          ) : (
            <>
              <Link to="/login">
                <button
                  style={{
                    backgroundColor: "#4a90e2",
                    color: "#fff",
                    border: "none",
                    borderRadius: "6px",
                    padding: "0.5rem 1rem",
                    fontSize: "1rem",
                    cursor: "pointer",
                  }}
                >
                  로그인
                </button>
              </Link>
              <Link to="/signup">
                <button
                  style={{
                    backgroundColor: "#4a90e2",
                    color: "#fff",
                    border: "none",
                    borderRadius: "6px",
                    padding: "0.5rem 1rem",
                    fontSize: "1rem",
                    cursor: "pointer",
                  }}
                >
                  회원가입
                </button>
              </Link>
            </>
          )}
        </div>
      </header>

      <main>
        <Outlet />
      </main>
    </div>
  );
};
