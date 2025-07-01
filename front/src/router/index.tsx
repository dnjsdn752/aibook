import { BrowserRouter, Routes, Route } from 'react-router-dom'
import SignupPage from '../features/User/SignupPage' // 파일 위치에 맞게

export default function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<SignupPage />} />
        {/* 필요한 라우트 더 추가 가능 */}
      </Routes>
    </BrowserRouter>
  )
}