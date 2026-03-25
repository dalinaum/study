import Link from "next/link"
import { prisma } from "@/lib/db"
import { CreateBoardDialog } from "@/components/create-board-dialog"

export default async function Home() {
  const boards = await prisma.board.findMany({
    orderBy: { createdAt: "desc" },
  })

  return (
    <div className="flex flex-1 flex-col items-center gap-6 p-8">
      <h1 className="text-3xl font-bold">칸반 보드</h1>
      <CreateBoardDialog />
      {boards.length === 0 ? (
        <p className="text-muted-foreground">보드가 없습니다</p>
      ) : (
        <ul className="grid w-full max-w-4xl grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {boards.map((board) => (
            <li
              key={board.id}
              className="rounded-lg border border-border p-4 transition-colors hover:bg-accent"
            >
              <Link href={`/boards/${board.id}`} className="block">
                <h2 className="text-lg font-semibold">{board.title}</h2>
                {board.description && (
                  <p className="mt-1 text-sm text-muted-foreground">
                    {board.description}
                  </p>
                )}
                <time
                  dateTime={board.createdAt.toISOString()}
                  className="mt-2 block text-xs text-muted-foreground"
                >
                  {board.createdAt.toLocaleDateString("ko-KR", {
                    year: "numeric",
                    month: "long",
                    day: "numeric",
                    hour: "2-digit",
                    minute: "2-digit",
                  })}
                </time>
              </Link>
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}
