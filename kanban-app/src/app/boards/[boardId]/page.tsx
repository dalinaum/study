import { notFound } from "next/navigation"
import { prisma } from "@/lib/db"
import { CreateTaskDialog } from "@/components/create-task-dialog"

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
      <div className="flex gap-4 overflow-x-auto">
        {board.columns.map((column) => (
          <div
            key={column.id}
            className="flex w-72 shrink-0 flex-col gap-3 rounded-lg border bg-muted/50 p-4"
          >
            <h2 className="font-semibold">{column.title}</h2>
            <div className="flex flex-1 flex-col gap-2">
              {column.tasks.map((task) => (
                <div
                  key={task.id}
                  className="rounded-md border bg-background p-3 shadow-sm"
                >
                  <p className="text-sm font-medium">{task.title}</p>
                  <div className="mt-1 flex items-center gap-2">
                    <span
                      className={`inline-block rounded px-1.5 py-0.5 text-xs font-medium ${
                        task.priority === "URGENT"
                          ? "bg-red-100 text-red-700 dark:bg-red-900/30 dark:text-red-400"
                          : task.priority === "HIGH"
                            ? "bg-orange-100 text-orange-700 dark:bg-orange-900/30 dark:text-orange-400"
                            : task.priority === "MEDIUM"
                              ? "bg-yellow-100 text-yellow-700 dark:bg-yellow-900/30 dark:text-yellow-400"
                              : "bg-gray-100 text-gray-700 dark:bg-gray-900/30 dark:text-gray-400"
                      }`}
                    >
                      {task.priority === "URGENT"
                        ? "긴급"
                        : task.priority === "HIGH"
                          ? "높음"
                          : task.priority === "MEDIUM"
                            ? "중"
                            : "낮음"}
                    </span>
                    {task.dueDate && (
                      <span className="text-xs text-muted-foreground">
                        {new Date(task.dueDate).toLocaleDateString("ko-KR")}
                      </span>
                    )}
                  </div>
                </div>
              ))}
            </div>
            <CreateTaskDialog columnId={column.id} />
          </div>
        ))}
      </div>
    </div>
  )
}
