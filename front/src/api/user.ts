import axios from "axios";

/**
 * 구독자 회원가입 API
 * 백엔드 구독자관리 서비스의 엔드포인트에 POST 요청
 */

const api = axios.create({
  baseURL: 'https://8088-dnjsdn752-aibook-kcc5uifn888.ws-us120.gitpod.io', // Gateway 주소
});

export const signupUser = (data: {
  email: string;
  password: string;
  userName: string;
}) => {
  return api.post(
    "/users",
    {
      ...data,
      isSubscription: false, // ⭐ 기본값
    }
  );
};

export const myData = (userId: number) => {
  return api.get(`/users/${userId}`);
};

export const myPoint = (userId: number) => {
  return api.get(`/points/${userId}`);
};

export const myReading = (userId: number) => {
  return api.get(`/reading/${userId}`);
};

//특정 id 책들만 가져오기
export const myBooks = (ids: number[]) => {
  const queryString = ids.map(id => `ids=${id}`).join('&');
  const url = `/books/mybooks?${queryString}`;
  if(ids.length == 0)
    return {
      data:[]
  }
  return api.get(url);
};


