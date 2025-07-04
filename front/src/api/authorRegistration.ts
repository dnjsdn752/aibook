import axios from 'axios';
import type { AuthorRegistrationForm } from '../features/AuthorRegistration/types/author';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

export const submitAuthorRegistration = (form: AuthorRegistrationForm) =>
  api.post("/authors/request", form);