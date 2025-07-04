import axios from 'axios';
import type { AuthorRegistrationForm } from '../features/AuthorRegistration/types/author';

const api = axios.create({
  baseURL: "https://8088-dnjsdn752-aibook-kcc5uifn888.ws-us120.gitpod.io", // ✅ 실제 게이트웨이 주소
});

export const submitAuthorRegistration = (form: AuthorRegistrationForm) =>
  api.post("/authors/request", form);