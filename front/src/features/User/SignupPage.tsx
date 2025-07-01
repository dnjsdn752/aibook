import { useForm } from 'react-hook-form'
import { signupUser } from '../../api/user'
import { yupResolver } from '@hookform/resolvers/yup'
import * as yup from 'yup'
import './SignupPage.css'

interface SignupFormData {
  email: string
  password: string
  name: string
}

const schema = yup.object({
    email: yup.string().email().required('이메일은 필수입니다.'),
    password: yup.string().min(6, '비밀번호는 최소 6자리입니다.').required('비밀번호는 필수입니다.'),
    name: yup.string().required('이름은 필수입니다.')
})

export default function SignupPage() {
    
        const { register, handleSubmit, formState: { errors } } = useForm<SignupFormData>({
            resolver: yupResolver(schema)
        })
        const onSubmit = async (data: SignupFormData) => {
            try {
                await signupUser(data)
                alert('회원가입 성공!')
            } catch (error) {
                alert('회원가입 실패!')
                console.error(error)
            }
        }

    return (
        <div className="signup-container">
            <form onSubmit={handleSubmit(onSubmit)} className="signup-card">
                <h2 className="signup-title">회원가입</h2>

                <div className="signup-field">
                <input type="email" {...register('email')} placeholder="이메일" />
                {errors.email && <span className="error-msg">{errors.email.message}</span>}
                </div>

                <div className="signup-field">
                <input type="password" {...register('password')} placeholder="비밀번호" />
                {errors.password && <span className="error-msg">{errors.password.message}</span>}
                </div>

                <div className="signup-field">
                <input type="text" {...register('name')} placeholder="이름" />
                {errors.name && <span className="error-msg">{errors.name.message}</span>}
                </div>

                <button type="submit" className="signup-button">가입하기</button>
            </form>
        </div>
    )
}