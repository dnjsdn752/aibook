import axios from 'axios'

// 기본 API 인스턴스
const api = axios.create({
  baseURL: 'https://8088-dnjsdn752-aibook-lehthwjj3h4.ws-us120.gitpod.io', // Gateway 주소
});

export interface SignupFormData {
  email: string
  password: string
  name: string
}

export const signupUser = async (payload: SignupFormData) => {
  return axios.post('localhost:5173/signup', payload)
}


// BuySubscriptionCommand 인터페이스 정의
export interface BuySubscriptionCommand {
  isSubscription: boolean;
}

// 사용자 구독 구매 API 요청
export const buySubscription = async (userId: number, payload: BuySubscriptionCommand) => {
  return await api.put(`/users/${userId}/buysubscription`, payload);
};

