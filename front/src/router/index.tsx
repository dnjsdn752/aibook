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
        path: "manuscripts/new",
        element: <ManuscriptEditor />,
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
