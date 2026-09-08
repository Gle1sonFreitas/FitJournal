import { useState, useEffect } from 'react'
import Style from '../styles/Main.module.css'
import { ModalRotina } from './Modal.jsx'

function Routines() {
    const [rotinas, setRotinas] = useState([])
    const [carregando, setCarregando] = useState(true)
    const [erro, setErro] = useState('')

    useEffect(() => {
        buscarRotinas()
    }, [])

    async function buscarRotinas() {
        const userId = localStorage.getItem('userId')
        try {
            const response = await fetch(`http://localhost:8080/treinos/${userId}`)
            if (!response.ok) throw new Error('Erro ao buscar rotinas.')
            const data = await response.json()
            setRotinas(data)
        } catch (error) {
            setErro(error.message)
        } finally {
            setCarregando(false)
        }
    }

    return (
        <main>
            <div className={Style.mainHeader}>
                <h2>Rotinas</h2>
            </div>

            <div className={Style.routinesContainer}>
                {carregando && <p className={Style.mensagem}>Carregando...</p>}
                {erro && <p className={Style.mensagem}>{erro}</p>}
                {!carregando && !erro && rotinas.length === 0 && (
                    <p className={Style.mensagem}>Nenhuma rotina cadastrada.</p>
                )}
                {rotinas.map((rotina, index) => (
                    <ModalRotina
                        key={index}
                        name={rotina.nomeRotina}
                        exerciseCount={rotina.qtdExercicios}
                        lastWorkout={rotina.ultimoTreino}
                    />
                ))}
            </div>

            <button className={Style.AddBtn}>+</button>
        </main>
    )
}

export default Routines
