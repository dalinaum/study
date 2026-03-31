import Link from "next/link"
import { prisma } from "@/lib/db"
import { CreateBoardDialog } from "@/components/create-board-dialog"

export default async function Home() {
  const boards = await prisma.board.findMany({
    orderBy: { createdAt: "desc" },
  })

  return (
    <>
      <header className="flex items-center justify-between border-b px-8 py-4">
        <h1 className="text-lg font-bold">Kanban Board</h1>
        <CreateBoardDialog />
      </header>
      <main className="flex flex-1 flex-col items-center gap-6 p-8">
        <div className="text-center">
          <h2 className="text-2xl font-bold">마이 보드</h2>
          <p className="mt-2 text-muted-foreground">
            작성한 Kanban 보드에서 선택해주세요
          </p>
        </div>
        {boards.length === 0 ? (
          <p className="text-muted-foreground">보드가 없습니다</p>
        ) : (
          <ul className="grid w-full max-w-5xl grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {boards.map((board) => (
              <li
                key={board.id}
                className="rounded-xl border border-border bg-background p-5 shadow-sm transition-colors hover:bg-accent"
              >
                <Link href={`/boards/${board.id}`} className="block">
                  <h3 className="text-lg font-semibold">{board.title}</h3>
                  {board.description && (
                    <p className="mt-1 text-sm text-muted-foreground">
                      {board.description}
                    </p>
                  )}
                  <time
                    dateTime={board.createdAt.toISOString()}
                    className="mt-3 block text-xs text-muted-foreground"
                  >
                    작성일: {board.createdAt.toLocaleDateString("ko-KR")}
                  </time>
                </Link>
              </li>
            ))}
          </ul>
        )}
      </main>
    </>
  )
}
