import { useState } from 'react'
import Style from '../styles/ModalNovaRotina.module.css'

const exercicioVazio = () => ({ nome: '', grupo: '', equipamento: '' })

function ModalNovaRotina({ onFechar, onSalvar }) {

    const [nomeRotina, setNomeRotina] = useState('')
    const [exercicios, setExercicios] = useState([exercicioVazio()])
    const [erro, setErro] = useState('')
    const [salvando, setSalvando] = useState(false)

    function atualizarExercicio(index, campo, valor) {
        const atualizados = exercicios.map((ex, i) =>
            i === index ? { ...ex, [campo]: valor } : ex
        )
        setExercicios(atualizados)
    }

    function adicionarExercicio() {
        setExercicios([...exercicios, exercicioVazio()])
    }

    function removerExercicio(index) {
        if (exercicios.length === 1) return
        setExercicios(exercicios.filter((_, i) => i !== index))
    }

    async function submeterRotina(evento) {
        evento.preventDefault()
        setErro('')
        setSalvando(true)

        const userId = sessionStorage.getItem('userId')

        try {

            const response = await fetch(`http://localhost:8080/treinos/add/${userId}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    nome: nomeRotina,
                    exercicios: exercicios.map((ex, i) => ({ id: i, ...ex })),
                }),
            })

            if (!response.ok) throw new Error('Erro ao salvar rotina.')

            onSalvar()
            
        } catch (err) {
            setErro(err.message)
        } finally {
            setSalvando(false)
        }
    }

    return (
        <div className={Style.overlay} onClick={onFechar}>
            <div className={Style.modal} onClick={evento => evento.stopPropagation()}>
                <div className={Style.header}>
                    <h2 className={Style.titulo}>Nova Rotina</h2>
                    <button className={Style.fecharBtn} onClick={onFechar}>✕</button>
                </div>

                <form className={Style.form} onSubmit={submeterRotina}>
                    <div className={Style.field}>
                        <label className={Style.label}>Nome da rotina</label>
                        <input
                            className={Style.input}
                            type="text"
                            placeholder="Ex: Treino A - Peito"
                            value={nomeRotina}
                            onChange={evento => setNomeRotina(evento.target.value)}
                            required
                        />
                    </div>

                    <div className={Style.exerciciosSection}>
                        <span className={Style.label}>Exercícios</span>

                        {exercicios.map((ex, index) => (
                            <div key={index} className={Style.exercicioRow}>
                                <div className={Style.exercicioFields}>
                                    <input
                                        className={Style.input}
                                        type="text"
                                        placeholder="Nome"
                                        value={ex.nome}
                                        onChange={evento => atualizarExercicio(index, 'nome', evento.target.value)}
                                        required
                                    />
                                    <input
                                        className={Style.input}
                                        type="text"
                                        placeholder="Grupo muscular"
                                        value={ex.grupo}
                                        onChange={evento => atualizarExercicio(index, 'grupo', evento.target.value)}
                                        required
                                    />
                                    <input
                                        className={Style.input}
                                        type="text"
                                        placeholder="Equipamento"
                                        value={ex.equipamento}
                                        onChange={evento => atualizarExercicio(index, 'equipamento', evento.target.value)}
                                        required
                                    />
                                </div>
                                <button
                                    type="button"
                                    className={Style.removerBtn}
                                    onClick={() => removerExercicio(index)}
                                    disabled={exercicios.length === 1}
                                >
                                    ✕
                                </button>
                            </div>
                        ))}

                        <button type="button" className={Style.adicionarBtn} onClick={adicionarExercicio}>
                            + Adicionar exercício
                        </button>
                    </div>

                    {erro && <p className={Style.erro}>{erro}</p>}

                    <div className={Style.acoes}>
                        <button type="button" className={Style.cancelarBtn} onClick={onFechar}>
                            Cancelar
                        </button>
                        <button type="submit" className={Style.salvarBtn} disabled={salvando}>
                            {salvando ? 'Salvando...' : 'Salvar'}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default ModalNovaRotina
