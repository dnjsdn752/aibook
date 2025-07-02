// front/src/Admin/components/AuthorRequestCard.tsx
import React from "react";
import "./AuthorRequestCard.css";

interface AuthorRequestCardProps {
  id: number | string;  // number에서 number | string으로 변경
  name: string;
  email: string;
  introduction: string;
  featuredWorks: string;
  onApprove: (id: number | string) => void;  // 작가 승인
  onReject: (id: number | string) => void;   // 작가 거절
}

const AuthorRequestCard: React.FC<AuthorRequestCardProps> = ({
  id,
  name,
  email,
  introduction,
  featuredWorks,
  onApprove,
  onReject,
}) => {
  return (
    <div className="author-request-card">
      <h3>{name}</h3>
      <p><strong>이메일:</strong> {email}</p>
      <p><strong>소개서:</strong> {introduction}</p>
      <p><strong>대표작:</strong> {featuredWorks}</p>
      <div className="btn-group">
        <button onClick={() => onApprove(id)} className="approve-btn">승인</button>
        <button onClick={() => onReject(id)} className="reject-btn">거절</button>
      </div>
    </div>
  );
};

export default AuthorRequestCard;