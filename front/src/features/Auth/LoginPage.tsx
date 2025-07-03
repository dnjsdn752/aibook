import React from "react";
import { useForm } from "react-hook-form";
import { login } from "../../api/auth";
import { useNavigate, Link } from "react-router-dom";

interface LoginForm {
  email: string;
  password: string;
}

export default function LoginPage() {
  const { register, handleSubmit } = useForm<LoginForm>();
  const navigate = useNavigate();

  const onSubmit = async (data: LoginForm) => {
    try {
      const response = await login(data);
      localStorage.setItem("token", response.data.token);
      alert("로그인 성공!");
      navigate("/");
    } catch (err) {
      alert("로그인 실패");
      console.error(err);
    }
  };

  return (
    <div
      style={{
        minHeight: "100vh",
        background: "linear-gradient(to right, #83a4d4, #b6fbff)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
      }}
    >
      {/* 상단 로고 버튼 */}
      <Link
        to="/"
        style={{
          textDecoration: "none",
          color: "#333",
          fontSize: "1.5rem",
          fontWeight: "bold",
          marginBottom: "2rem",
        }}
      >
        뒤로가기
      </Link>

      <form
        onSubmit={handleSubmit(onSubmit)}
        style={{
          background: "#fff",
          padding: "2rem",
          borderRadius: "8px",
          boxShadow: "0 4px 12px rgba(0,0,0,0.2)",
          width: "100%",
          maxWidth: "400px",
          display: "flex",
          flexDirection: "column",
          gap: "1rem",
        }}
      >
        <h2 style={{ textAlign: "center", color: "#333" }}>로그인</h2>

        <input
          type="email"
          {...register("email")}
          placeholder="이메일"
          style={{
            padding: "0.75rem",
            borderRadius: "6px",
            border: "1px solid #ccc",
            fontSize: "1rem",
          }}
        />

        <input
          type="password"
          {...register("password")}
          placeholder="비밀번호"
          style={{
            padding: "0.75rem",
            borderRadius: "6px",
            border: "1px solid #ccc",
            fontSize: "1rem",
          }}
        />

        <button
          type="submit"
          style={{
            backgroundColor: "#4a90e2",
            color: "#fff",
            border: "none",
            borderRadius: "6px",
            padding: "0.75rem",
            fontSize: "1rem",
            cursor: "pointer",
            transition: "background-color 0.2s",
          }}
          onMouseEnter={(e) => {
            (e.currentTarget as HTMLButtonElement).style.backgroundColor = "#357ABD";
          }}
          onMouseLeave={(e) => {
            (e.currentTarget as HTMLButtonElement).style.backgroundColor = "#4a90e2";
          }}
        >
          로그인
        </button>
      </form>
    </div>
  );
}
