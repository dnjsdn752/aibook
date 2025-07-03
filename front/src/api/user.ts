import axios from "axios";

/**
 * 구독자 회원가입 API
 * 백엔드 구독자관리 서비스의 엔드포인트에 POST 요청
 */
export const signupUser = (data: {
  email: string;
  password: string;
  userName: string;
}) => {
  return axios.post(
    "https://8088-dnjsdn752-aibook-kcc5uifn888.ws-us120.gitpod.io/users",
    {
      ...data,
      isSubscription: false, // ⭐ 기본값
    }
  );
};

