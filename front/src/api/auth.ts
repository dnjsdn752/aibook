import axios from "axios";

export const login = (data: { email: string; password: string }) => {
  return axios.post(
    "https://8088-dnjsdn752-aibook-kcc5uifn888.ws-us120.gitpod.io/users/login",
    data
  );
};
