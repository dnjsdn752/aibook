// src/router/index.tsx
import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";
import AuthorRegistration from "../features/AuthorRegistration/pages/AuthorRegistration";
import AdminDashboard from "../features/Admin/pages/AdminDashboard";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout isAuthor={true} />,
    children: [
      {
        index: true, // "/"
        element: <LibraryList />,
      },
      {
        path: "mypage",
        element: (
          <div>
            <h2>마이페이지</h2>
            <p>여기에 회원 정보나 구독 정보가 표시됩니다.</p>
          </div>
        ),
      },
      {
        path: "manuscripts/new",
        element: <ManuscriptEditor />,
      },
      {
        path: "author/registration", // 작가 등록 페이지 경로 추가
        element: <AuthorRegistration />,
      },
      {
        path: "admin", // 관리자 대시보드 경로 추가
        element: <AdminDashboard />,
      },
    ],
  },
]);
