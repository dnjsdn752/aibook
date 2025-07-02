// front/src/Admin/pages/AdminDashboard.tsx
import React, { useEffect, useState } from "react";
import "./AdminDashboard.css";
import AuthorRequestCard from "../AuthorRequestCard";
import { fetchAuthorRequests, approveAuthor, rejectAuthor } from "../../../api/admin";

interface AuthorRequest {
  id: number | string;
  name: string;
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
      .then((data: AuthorRequest[]) => setRequests(data))
      .catch(() => setError("신청 목록을 불러오는 중 오류가 발생했습니다."))
      .finally(() => setLoading(false));
  }, []);

  const handleApprove = async (id: number | string) => {
    setLoading(true);
    try {
      await approveAuthor(id);
      setRequests((prev) => prev.filter((req) => req.id !== id));
      alert("작가 승인 처리되었습니다.");
    } catch (e) {
      console.error(e);
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
      console.error(e);
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
          name={req.name}
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
