import axios from 'axios'

export interface SignupFormData {
  email: string
  password: string
  name: string
}

export const signupUser = async (payload: SignupFormData) => {
  return axios.post('localhost:5173/signup', payload)
}