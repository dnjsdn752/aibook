import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";

export const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <Layout>
        <LibraryList />
      </Layout>
    ),
  },
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
]);
