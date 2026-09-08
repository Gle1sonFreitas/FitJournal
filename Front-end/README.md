# FitJournal — Front-end

Aplicação web para gerenciamento de rotinas de treino. Permite cadastrar rotinas, visualizar exercícios e acompanhar o histórico de treinos.

## Stack

- **React 19** + **Vite 8** (JavaScript, sem TypeScript)
- **CSS Modules** para estilização por componente
- **oxlint** como linter

## Comandos

```bash
npm install      # Instalar dependências
npm run dev      # Servidor de desenvolvimento (http://localhost:5173)
npm run build    # Build de produção
npm run preview  # Prévia do build local
npm run lint     # Executar oxlint
```

## Estrutura

```
src/
├── componentes/
│   ├── Navbar.jsx      # Barra de navegação + sidebar deslizante
│   ├── Main.jsx        # Tela principal com lista de rotinas
│   └── Modal.jsx       # Card de rotina (type=1: exibe nome, exercícios, último treino)
├── styles/
│   ├── Navbar.module.css
│   ├── Main.module.css
│   └── Modal.module.css
├── App.jsx
├── main.jsx
└── index.css           # Reset CSS global
```

## Funcionalidades implementadas

- Navbar com logo e sidebar deslizante (abre/fecha com overlay)
- Lista de rotinas em cards com scroll interno
- Card de rotina: nome, quantidade de exercícios, data do último treino, botões Editar e Iniciar
- Botão flutuante (+) fixo no canto inferior direito para adicionar rotinas
