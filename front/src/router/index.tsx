import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";

export const router = createBrowserRouter([

  {
    path: "/mypage",
    element: (
      <Layout>
        <div>
          <h2>마이페이지</h2>
          <p>여기에 회원 정보나 구독 정보가 표시됩니다.</p>
        </div>
      </Layout>
    ),
  },
  {
    path: "/",
    element: <LibraryList />,
  },
  {
    path: "/manuscripts/new",
    element: <ManuscriptEditor />,
  },
]);
