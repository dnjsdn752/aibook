import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";
import SignupPage from "../features/Auth/SignupPage";
import LoginPage from "../features/Auth/LoginPage";
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
    element: <Layout isAuthor={true} />, // 필요 시 isAuthor를 true/false로 변경
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
            <Stack spacing={4} mt={4}>
              <BuySubscriptionButton userId={1} />
              <BuyPointButton userId={1} />
            </Stack>
          </div>
        ),
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