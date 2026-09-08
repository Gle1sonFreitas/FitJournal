# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
npm run dev       # Start dev server (Vite HMR)
npm run build     # Production build
npm run preview   # Preview production build locally
npm run lint      # Run oxlint
```

## Architecture

React 19 + Vite 8 app written in plain JavaScript (`.jsx`, no TypeScript). Uses `npm`.

- Entry: `index.html` → `src/main.jsx` → `src/App.jsx`
- Linter: **oxlint** (Rust-based, not ESLint). Config in `.oxlintrc.json` — enforces React hooks rules.
- No routing, state management, UI library, or test framework is set up yet — this is a greenfield project.

When adding features, create subdirectories under `src/` as needed (e.g., `src/components/`, `src/pages/`, `src/hooks/`).
