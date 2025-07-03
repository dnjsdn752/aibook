import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";
import SignupPage from "../features/Auth/SignupPage";
import LoginPage from "../features/Auth/LoginPage";
import BuySubscriptionButton from "../features/User/BuySubscription";
import BuyPointButton from "../features/User/BuyPoint";
import { Stack } from "@mui/material";
import BookDetail from "../features/Book/BookDetail";
import BorrowedBookList from "../features/User/BorrowedBookList"; // ✅ 추가된 대여목록 컴포넌트

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
    element: <Layout isAuthor={true} />,
    children: [
      {
        index: true,
        element: <LibraryList />,
      },
      {
        path: "mypage",
        element: (
          <>
            {/* ✅ 기존 마크업 유지 */}
            <div>
              <h2>마이페이지</h2>
              <p>여기에 회원 정보나 구독 정보가 표시됩니다.</p>
              <Stack spacing={4} mt={4}>
                <BuySubscriptionButton userId={1} />
                <BuyPointButton userId={1} />
              </Stack>
            </div>

            {/* ✅ 하단에 대여한 책 목록 카드 추가 */}
            <BorrowedBookList userId={1} />
          </>
        ),
      },
      {
        path: "manuscripts/new",
        element: <ManuscriptEditor />,
      },
      {
        path: "book/:bookId",
        element: <BookDetail />,
      },
    ],
  },
]);
