import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

export interface BuySubscriptionCommand {
  isSubscription: boolean;
}

export const buySubscription = async (userId: number, payload: BuySubscriptionCommand) => {
  return await api.put(`/users/${userId}/buysubscription`, payload);
};

export interface BuyPointCommand {
  userId: number;
  amount: number;
}

export const buyPoint = async (payload: BuyPointCommand) => {
  return await api.post(`/points/charged`, payload);
};
