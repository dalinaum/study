import { notFound } from "next/navigation"
import { prisma } from "@/lib/db"

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
      <div className="flex gap-4 overflow-x-auto">
        {board.columns.map((column) => (
          <div
            key={column.id}
            className="flex w-72 shrink-0 flex-col rounded-lg border bg-muted/50 p-4"
          >
            <h2 className="font-semibold">{column.title}</h2>
          </div>
        ))}
      </div>
    </div>
  )
}
