# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

- **Dev server:** `npm run dev`
- **Build:** `npm run build`
- **Lint:** `npm run lint` (ESLint 9 flat config with Next.js core-web-vitals + TypeScript rules)
- **Prisma generate:** `npx prisma generate` (outputs to `src/generated/prisma/`)
- **Prisma migrate:** `npx prisma migrate dev`

## Architecture

Next.js 16 App Router kanban board app with PostgreSQL via Prisma.

- **Frontend:** React 19, Tailwind CSS 4, @dnd-kit/sortable for drag-and-drop
- **UI components:** `src/components/ui/` using @base-ui/react primitives with class-variance-authority variants
- **Utility:** `src/lib/utils.ts` exports `cn()` (clsx + tailwind-merge)
- **Path alias:** `@/*` maps to `./src/*`

## Data Model

Board → Column → Task (cascading deletes). Columns have unique position per board; tasks have unique position per column. Tasks have a `Priority` enum (LOW, MEDIUM, HIGH, URGENT).

Prisma client is generated into `src/generated/prisma/` — run `npx prisma generate` after schema changes.
