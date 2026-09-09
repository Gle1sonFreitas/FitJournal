import { useState } from 'react'
import Style from '../styles/Auth.module.css'
import Cadastro from './Cadastro.jsx'

function Login() {
    const [showCadastro, setShowCadastro] = useState(false)
    const [form, setForm] = useState({ email: '', password: '' })
    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)

    if (showCadastro) {
        return <Cadastro onSwitch={() => setShowCadastro(false)} />
    }

    function atualizarCampo(evento) {
        setForm({ ...form, [evento.target.name]: evento.target.value })
    }

    async function submeterLogin(evento) {
        evento.preventDefault()
        setError('')
        setLoading(true)
        try {

            const response = await fetch('http://localhost:8080/users/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email: form.email, senha: form.password }),
            })

            if (!response.ok) {
                throw new Error('E-mail ou senha incorretos.')
            }

            const data = await response.json()

            sessionStorage.setItem('userId', data.id)
            sessionStorage.setItem('userName', data.userName)

            window.location.reload()

        } catch (err) {
            setError(err.message)
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className={Style.page}>
            <div className={Style.card}>
                <h1 className={Style.logo}>
                    <span><span className={Style.logoDetail}>Fit</span>Journal</span>
                </h1>

                <p className={Style.subtitle}>Bem-vindo de volta</p>

                <form className={Style.form} onSubmit={submeterLogin}>
                    <div className={Style.field}>
                        <label className={Style.label}>E-mail</label>
                        <input
                            className={Style.input}
                            type="email"
                            name="email"
                            placeholder="seu@email.com"
                            value={form.email}
                            onChange={atualizarCampo}
                            required
                        />
                    </div>

                    <div className={Style.field}>
                        <label className={Style.label}>Senha</label>
                        <input
                            className={Style.input}
                            type="password"
                            name="password"
                            placeholder="••••••••"
                            value={form.password}
                            onChange={atualizarCampo}
                            required
                        />
                    </div>

                    {error ? <p className={Style.error}>{error}</p> : null}

                    <button className={Style.submitBtn} type="submit" disabled={loading}>
                        {loading ? 'Aguarde...' : 'Entrar'}
                    </button>
                </form>

                <p className={Style.toggle}>
                    Não tem uma conta?
                    <button className={Style.toggleBtn} onClick={() => setShowCadastro(true)}>
                        Cadastre-se
                    </button>
                </p>
            </div>
        </div>
    )
}

export default Login
