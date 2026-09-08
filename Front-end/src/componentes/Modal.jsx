import Style from '../styles/Modal.module.css'

export function ModalRotina({ name, exerciseCount, lastWorkout }) {
    return (
        <div className={Style.routineModal}>
            <div className={Style.info}>
                <h3 className={Style.name}>{name ?? 'Rotina sem nome'}</h3>
                <p className={Style.meta}>
                    <span>Exercícios: {exerciseCount ?? 0}</span>
                    <span className={Style.dot}>·</span>
                    <span>Último: {lastWorkout ?? '—'}</span>
                </p>
            </div>
            <div className={Style.actions}>
                <button className={Style.editBtn}>Editar</button>
                <button className={Style.startBtn}>▶</button>
            </div>
        </div>
    )
}
