import { useState } from 'react'
import Style from '../styles/Auth.module.css'

function Cadastro({ onSwitch }) {
    const [form, setForm] = useState({ name: '', email: '', password: '' })
    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)

    function atualizarCampo(evento) {
        setForm({ ...form, [evento.target.name]: evento.target.value })
    }

    async function submeterCadastro(evento) {
        evento.preventDefault()
        setError('')
        setLoading(true)

        try {

            const cadastroRes = await fetch('http://localhost:8080/users/cadastro', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ userName: form.name, email: form.email, password: form.password }),
            })

            if (!cadastroRes.ok) throw new Error('Erro ao criar conta.')

            onSwitch()
            
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
                    <span className={Style.logoDetail}>Fit</span>Journal
                </h1>
                <p className={Style.subtitle}>Crie sua conta</p>

                <form className={Style.form} onSubmit={submeterCadastro}>
                    <div className={Style.field}>
                        <label className={Style.label}>Nome</label>
                        <input
                            className={Style.input}
                            type="text"
                            name="name"
                            placeholder="Seu nome"
                            value={form.name}
                            onChange={atualizarCampo}
                            required
                        />
                    </div>

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

                    {error && <p className={Style.error}>{error}</p>}

                    <button className={Style.submitBtn} type="submit" disabled={loading}>
                        {loading ? 'Aguarde...' : 'Criar conta'}
                    </button>
                </form>

                <p className={Style.toggle}>
                    Já tem uma conta?
                    <button className={Style.toggleBtn} onClick={onSwitch}>
                        Entrar
                    </button>
                </p>
            </div>
        </div>
    )
}

export default Cadastro
