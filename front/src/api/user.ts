import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

export const signupUser = (data: {
  email: string;
  password: string;
  userName: string;
}) => {
  return api.post("/users", {
    ...data,
    isSubscription: false,
  });
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

export const myBooks = (ids: number[]) => {
  if (ids.length === 0) {
    return { data: [] };
  }
  const queryString = ids.map(id => `ids=${id}`).join('&');
  const url = `/books/mybooks?${queryString}`;
  return api.get(url);
};
