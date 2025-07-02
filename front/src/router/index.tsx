// src/router/index.tsx
import React from "react";
import { createBrowserRouter } from "react-router-dom";
import { Layout } from "../components/Layout";
import { LibraryList } from "../features/Library/LibraryList";
import ManuscriptEditor from "../features/Manuscript/ManuscriptEditor";
import BuySubscriptionButton from "../features/User/BuySubscription"; 
import BuyPointButton from "../features/User/BuyPoint";
import { Stack } from "@mui/material";

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
            <Stack spacing={4} mt={4}>
              <BuySubscriptionButton userId={1} />
              <BuyPointButton userId={1}/>
            </Stack>

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
