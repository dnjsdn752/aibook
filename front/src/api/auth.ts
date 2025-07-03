import axios from "axios";

export const login = (data: { email: string; password: string }) => {
  return axios.post(
    "https://expert-parakeet-g4rx999gq7w5h97r-8088.app.github.dev/users/login",
    data
  );
};
