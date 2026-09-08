import { useState } from 'react'
import Styles from '../styles/Navbar.module.css'

function Navbar(){
  const [sidebar, setSidebar] = useState(false);

  function deslogar() {
    localStorage.removeItem('userId')
    localStorage.removeItem('userName')
    window.location.reload()
  }

  return(
    <>
      <nav>
        <ul className={Styles.navbar}>
          <li>
            <button className={Styles.menuBtn} onClick={() => setSidebar(true)}>
              ☰
            </button>
          </li>
          <li className={Styles.logo}><span className={Styles.logoDetail}>Fit</span>Journal</li>
          <li className={Styles.profile}>Perfil</li>
        </ul>
      </nav>

      {sidebar ? <div className={Styles.overlay} onClick={() => setSidebar(false)} /> : null}

      <aside className={`${Styles.sidebar} ${sidebar ? Styles.sidebarOpen : ''}`}>
        <div className={Styles.sidebarHeader}>
          <span className={Styles.sidebarLogo} ><span className={Styles.logoDetail} >Fit</span>Journal</span>  
          <button className={Styles.closeBtn} onClick={() => setSidebar(false)}>
            ✕
          </button>
        </div>
        <ul className={Styles.sidebarNav}>
          <li>Início</li>
          <li>Rotinas</li>
          <li>Configurações</li>
        </ul>

        <button className={Styles.logoutBtn} onClick={deslogar}>Sair</button>
      </aside>
    </>
  )
}

export default Navbar
