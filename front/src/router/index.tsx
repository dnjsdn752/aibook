// src/router/index.tsx
import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";
import BuySubscriptionButton from "../features/User/BuySubscription"; 

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
            <BuySubscriptionButton userId={1} />
          </div>
        ),
      },
      {
        path: "manuscripts/new",
        element: <ManuscriptEditor />,
      },
    ],
  },
]);
