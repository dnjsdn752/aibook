import axios from "axios";

export const login = (data: { email: string; password: string }) => {
  return axios.post(
    `${import.meta.env.VITE_API_BASE_URL}/users/login`,
    data
  );
};
