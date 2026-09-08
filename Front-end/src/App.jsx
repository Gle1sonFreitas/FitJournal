import Navbar from './componentes/Navbar.jsx'
import Routines from './componentes/Routines.jsx'
import Login from './componentes/Login.jsx'

function App() {
  const usuarioAutenticado = localStorage.getItem('userId') && localStorage.getItem('userName')

  if (!usuarioAutenticado) {
    return <Login />
  }

  return (
    <div>
      <Navbar />
      <Routines />
    </div>
  )
}

export default App
