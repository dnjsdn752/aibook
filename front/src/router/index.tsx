// src/router/index.tsx
import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";
import MyDraftList from "../features/Manuscript/MyDraftList";

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
        path: "manuscripts",
        children: [
          {
            index: true,
            element: <MyDraftList />,
          },
          {
            path: "editor", // 새 원고 작성
            element: <ManuscriptEditor />,
          },
          {
            path: ":id", // 기존 원고 수정
            element: <ManuscriptEditor />,
          },
        ],
      },
    ],
  },
]);
