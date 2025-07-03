import React from "react";
import "./AuthorRequestCard.css";

interface AuthorRequestCardProps {
  id: number | string;
  authorName: string;
  email: string;
  introduction: string;
  featuredWorks: string;
  onApprove: (id: number | string) => void;
  onReject: (id: number | string) => void;
}

const AuthorRequestCard: React.FC<AuthorRequestCardProps> = ({
  id,
  authorName,
  email,
  introduction,
  featuredWorks,
  onApprove,
  onReject,
}) => {
  const handleApproveClick = () => {
    if (confirm(`작가 "${authorName}"을(를) 승인하시겠습니까?`)) {
      onApprove(id);
    }
  };

  const handleRejectClick = () => {
    if (confirm(`작가 "${authorName}"을(를) 거절하시겠습니까?`)) {
      onReject(id);
    }
  };

  return (
    <div className="author-request-card">
      <h3>{authorName}</h3>
      <p><strong>이메일:</strong> {email}</p>
      <p><strong>소개서:</strong> {introduction}</p>
      <p><strong>대표작:</strong> {featuredWorks}</p>
      <div className="button-group">
        <button onClick={handleApproveClick} className="approve-btn">
          승인
        </button>
        <button onClick={handleRejectClick} className="reject-btn">
          거절
        </button>
      </div>
    </div>
  );
};

export default AuthorRequestCard;
