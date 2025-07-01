import axios from 'axios'

interface SignupPayload {
  email: string
  password: string
  name: string
}

export const signupUser = async (payload: SignupPayload) => {
  return axios.post('/api/signup', payload)
}