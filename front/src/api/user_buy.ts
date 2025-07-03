import axios from 'axios'

// 기본 API 인스턴스
const api = axios.create({
  baseURL: 'https://expert-parakeet-g4rx999gq7w5h97r-8088.app.github.dev', // Gateway 주소
});



// BuySubscriptionCommand 인터페이스 정의
export interface BuySubscriptionCommand {
  isSubscription: boolean;
}

// 사용자 구독 구매 API 요청
export const buySubscription = async (userId: number, payload: BuySubscriptionCommand) => {
  return await api.put(`/users/${userId}/buysubscription`, payload);
};

// BuyPointCommand 인터페이스 정의
export interface BuyPointCommand {
  userId: number;
  amount : number;
}

// 사용자 포인트 구매 API 요청
export const buyPoint = async (payload: BuyPointCommand) => {
  return await api.post(`/points/charged`, payload);
};
