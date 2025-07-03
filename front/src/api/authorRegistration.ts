import axios from 'axios';
import type { AuthorRegistrationForm } from '../features/AuthorRegistration/types/author';

const api = axios.create({
  baseURL: "https://super-duper-space-xylophone-x4gxwvq7xrwf69gx-8088.app.github.dev/", // ✅ 실제 게이트웨이 주소
});

export const submitAuthorRegistration = (form: AuthorRegistrationForm) =>
  api.post("/authors/request", form);