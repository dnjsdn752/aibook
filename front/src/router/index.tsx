import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";
import SignupPage from "../features/Auth/SignupPage";
import LoginPage from "../features/Auth/LoginPage";
import MyPage from "../features/User/MyPage"
import BuySubscriptionButton from "../features/User/BuySubscription";
import BuyPointButton from "../features/User/BuyPoint";
import AuthorRegistration from "../features/AuthorRegistration/pages/AuthorRegistration";
import AdminDashboard from "../features/Admin/pages/AdminDashboard";
import { Stack } from "@mui/material";
import MyDraftList from "../features/Manuscript/MyDraftList";

export const router = createBrowserRouter([
  {
    path: "/signup",
    element: <SignupPage />,
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/",
    element: <Layout />, // ✅ 더 이상 isAuthor={true} 넘기지 않음
    children: [
      {
        index: true, // "/"
        element: <LibraryList />,
      },
      {
        path: "mypage",
        element: <MyPage />,
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
            path: "editor/:id", // 기존 원고 수정
            element: <ManuscriptEditor />,
          },
        ],
      },
      {
        path: "author/registration",
        element: <AuthorRegistration />,
      },
      {
        path: "admin",
        element: <AdminDashboard />,
      },
    ],
  },
]);
