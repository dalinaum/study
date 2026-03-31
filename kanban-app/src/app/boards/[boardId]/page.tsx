import { notFound } from "next/navigation"
import { prisma } from "@/lib/db"
import { KanbanBoard } from "@/components/kanban-board"

export default async function BoardPage({
  params,
}: {
  params: Promise<{ boardId: string }>
}) {
  const { boardId } = await params

  const board = await prisma.board.findUnique({
    where: { id: boardId },
    include: {
      columns: {
        orderBy: { position: "asc" },
        include: {
          tasks: {
            orderBy: { position: "asc" },
          },
        },
      },
    },
  })

  if (!board) {
    notFound()
  }

  return (
    <div className="flex flex-1 flex-col gap-6 p-6">
      <div>
        <h1 className="text-2xl font-bold">{board.title}</h1>
        {board.description && (
          <p className="mt-1 text-sm text-muted-foreground">
            {board.description}
          </p>
        )}
      </div>
      <KanbanBoard
        key={board.columns.map((c) => `${c.id}:${c.tasks.map((t) => t.id).join(",")}`).join("|")}
        board={board}
      />
    </div>
  )
}
