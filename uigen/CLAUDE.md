# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

UIGen is an AI-powered React component generator with live preview. Users describe components in natural language via chat, Claude AI generates the code with streaming tool calls, and a split-pane UI shows live preview + code editor. Files exist only in an in-memory virtual file system (no disk writes).

## Commands

```bash
npm run setup              # Install deps + prisma generate + prisma migrate dev
npm run dev                # Dev server with Turbopack (port 3000)
npm run build              # Production build
npm run test               # Run all tests (vitest)
npx vitest run src/lib/__tests__/file-system.test.ts   # Run a single test file
npm run lint               # ESLint
npm run db:reset           # Reset SQLite database (destructive)
```

**Note on cross-platform**: The `dev` script uses `NODE_OPTIONS='--require ./node-compat.cjs'` with single quotes, which only works on Unix shells. On Windows, use `cross-env` with double quotes or run via WSL/Git Bash.

## Architecture

### Data Flow

```
Chat input → /api/chat (streaming) → Claude AI (with tools)
  → str_replace_editor / file_manager tool calls
  → VirtualFileSystem (in-memory)
  → FileSystemContext (React state)
  → CodeEditor (Monaco) + PreviewFrame (iframe with Babel transform)
```

### Key Layers

- **API route** (`src/app/api/chat/route.ts`): Streaming endpoint using Vercel AI SDK. Sends virtual file system state as context. Saves project to DB on stream finish.
- **AI tools** (`src/lib/tools/`): `str_replace_editor` (create/view/replace files) and `file_manager` (rename/delete). These modify the virtual file system, not real files.
- **Virtual file system** (`src/lib/file-system.ts`): `VirtualFileSystem` class — in-memory file storage with serialization for DB persistence.
- **Preview** (`src/lib/transform/jsx-transformer.ts`): Babel standalone transforms JSX → executable JS. Uses esm.sh CDN import maps. Renders in sandboxed iframe.
- **Provider** (`src/lib/provider.ts`): Returns Claude Haiku model, or a `MockLanguageModel` if no API key is set. Mock returns static components for demo purposes.
- **Auth** (`src/lib/auth.ts`): JWT sessions in HTTP-only cookies (7-day expiry) using `jose`. Server actions for sign-up/sign-in.
- **Contexts** (`src/lib/contexts/`): `ChatContext` wraps Vercel AI SDK's `useChat`; `FileSystemContext` manages virtual file state and processes tool calls.

### Database

SQLite via Prisma. Schema in `prisma/schema.prisma`:
- `User`: email/password auth
- `Project`: stores messages (JSON) and virtual file system data (JSON). Optional userId for anonymous usage.

Prisma client is generated to `src/generated/prisma`.

### Node.js Compatibility

`node-compat.cjs` removes `globalThis.localStorage/sessionStorage` on the server to fix Node.js 25+ SSR issues where the experimental Web Storage API breaks SSR guard checks.

## Path Alias

`@/*` maps to `./src/*` (configured in tsconfig.json).

## Code Style

- Use comments sparingly. Only comment complex code.

## Environment

- `ANTHROPIC_API_KEY` in `.env` — optional. Without it, the mock provider returns static components.
