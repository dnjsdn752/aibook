import React, { useState } from "react";
import "./AuthorRegistration.css";
import { submitAuthorRegistration } from "../../../api/authorRegistration"; // api 경로 맞게 조정
import type { AuthorRegistrationForm } from "../types/author";

const AuthorRegistration = () => {
  const [form, setForm] = useState<AuthorRegistrationForm>({
    authorName: "",
    email: "",
    introduction: "",
    featuredWorks: "",
  });

   const [loading, setLoading] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault(); 
    try {
      await submitAuthorRegistration(form);
      alert("작가 등록 신청 완료!");
      setForm({ authorName: "", email: "", introduction: "", featuredWorks: "" }); // 폼 초기화
    } catch (error) {
      alert("등록 신청 중 오류가 발생했습니다. 다시 시도해주세요.");
      console.error(error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">작가 등록 신청</h1>
      <form onSubmit={handleSubmit}>
        <label>
          작가 이름 <span className="required">*</span>
        </label>
        <input
          type="text"
          name="authorName"
          value={form.authorName}
          onChange={handleChange}
          required
          placeholder="예: 홍길동"
        />

        <label>
          이메일 <span className="required">*</span>
        </label>
        <input
          type="email"
          name="email"
          value={form.email}
          onChange={handleChange}
          required
          placeholder="예: example@example.com"
        />

        <label>
          작가 소개서 <span className="required">*</span>
        </label>
        <textarea
          name="introduction"
          value={form.introduction}
          onChange={handleChange}
          required
          placeholder="간단한 자기소개와 활동 경력을 작성해주세요."
        />

        <label>
          대표작 <span className="required">*</span>
        </label>
        <input
          type="text"
          name="featuredWorks"
          value={form.featuredWorks}
          onChange={handleChange}
          required
          placeholder="대표 작품명 또는 출판사"
        />

        <button type="submit" className="submit-btn">
          등록 신청
        </button>
      </form>
    </div>
  );
};

export default AuthorRegistration;
