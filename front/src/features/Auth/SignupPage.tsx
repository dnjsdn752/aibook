import React from "react";
import { useForm } from "react-hook-form";
import { signupUser } from "../../api/user";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { Link } from "react-router-dom";

interface SignupFormData {
  email: string;
  password: string;
  name: string;
}

const schema = yup.object({
  email: yup.string().email("올바른 이메일을 입력하세요").required("이메일은 필수입니다."),
  password: yup.string().min(6, "비밀번호는 최소 6자리입니다.").required("비밀번호는 필수입니다."),
  name: yup.string().required("이름은 필수입니다."),
});

export default function SignupPage() {
  const { register, handleSubmit, formState: { errors } } = useForm<SignupFormData>({
    resolver: yupResolver(schema)
  });

  const onSubmit = async (data: SignupFormData) => {
    try {
      const payload = {
        email: data.email,
        password: data.password,
        userName: data.name, // ✅ User 엔티티에 맞춤
      };

      await signupUser(payload);
      alert("회원가입 성공!");
      window.location.href = "/";
    } catch (error) {
      alert("회원가입 실패!");
      console.error(error);
    }
  };

  return (
    <div
      style={{
        minHeight: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "linear-gradient(to right, #83a4d4, #b6fbff)",
        flexDirection: "column",
      }}
    >
      {/* 상단 메인 페이지 이동 로고 */}
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
        <h2
          style={{
            textAlign: "center",
            marginBottom: "1rem",
            color: "#333",
          }}
        >
          회원가입
        </h2>

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
        {errors.email && (
          <span style={{ color: "red", fontSize: "0.9rem" }}>
            {errors.email.message}
          </span>
        )}

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
        {errors.password && (
          <span style={{ color: "red", fontSize: "0.9rem" }}>
            {errors.password.message}
          </span>
        )}

        <input
          type="text"
          {...register("name")}
          placeholder="이름"
          style={{
            padding: "0.75rem",
            borderRadius: "6px",
            border: "1px solid #ccc",
            fontSize: "1rem",
          }}
        />
        {errors.name && (
          <span style={{ color: "red", fontSize: "0.9rem" }}>
            {errors.name.message}
          </span>
        )}

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
          가입하기
        </button>
      </form>
    </div>
  );
}
