import React, { useEffect, useState } from "react";
import "./AdminDashboard.css";
import AuthorRequestCard from "../AuthorRequestCard";
import { fetchAuthorRequests, approveAuthor, rejectAuthor } from "../../../api/admin";

interface AuthorRequest {
  id: number | string;
  authorName: string;
  email: string;
  introduction: string;
  featuredWorks: string;
}

const AdminDashboard = () => {
  const [requests, setRequests] = useState<AuthorRequest[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

useEffect(() => {
  fetchAuthorRequests()
    .then((data) => {
      console.log("작가 요청 목록:", data);
      if (Array.isArray(data)) {
        setRequests(data);
      } else {
        console.error("응답이 배열이 아닙니다:", data);
        setError("데이터 형식이 올바르지 않습니다.");
      }
    })
    .catch((err) => {
      console.error("목록 불러오기 실패:", err);
      setError("신청 목록을 불러오는 중 오류가 발생했습니다.");
    })
    .finally(() => setLoading(false));
}, []);

  const handleApprove = async (id: number | string) => {
    setLoading(true);
    try {
      await approveAuthor(id);
      setRequests((prev) => prev.filter((req) => req.id !== id));
      alert("작가 승인 처리되었습니다.");
    } catch (e) {
      console.error("승인 실패:", e);
      alert("승인 처리 중 오류가 발생했습니다.");
    } finally {
      setLoading(false);
    }
  };

  const handleReject = async (id: number | string) => {
    setLoading(true);
    try {
      await rejectAuthor(id);
      setRequests((prev) => prev.filter((req) => req.id !== id));
      alert("작가 거절 처리되었습니다.");
    } catch (e) {
      console.error("거절 실패:", e);
      alert("거절 처리 중 오류가 발생했습니다.");
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <p>로딩 중...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="admin-dashboard">
      <h2>작가 등록 신청 목록</h2>
      {requests.length === 0 && <p>등록 대기 중인 작가가 없습니다.</p>}
      {requests.map((req) => (
        <AuthorRequestCard
          key={req.id}
          id={req.id}
          authorName={req.authorName}
          email={req.email}
          introduction={req.introduction}
          featuredWorks={req.featuredWorks}
          onApprove={handleApprove}
          onReject={handleReject}
        />
      ))}
    </div>
  );
};

export default AdminDashboard;
