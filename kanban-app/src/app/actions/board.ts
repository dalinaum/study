"use server"

import { redirect } from "next/navigation"
import { prisma } from "@/lib/db"

export async function createBoard(prevState: unknown, formData: FormData) {
  const title = formData.get("title") as string | null
  const description = formData.get("description") as string | null

  if (!title || title.trim() === "") {
    return { error: "제목을 입력해주세요." }
  }

  const board = await prisma.board.create({
    data: {
      title: title.trim(),
      description: description?.trim() || null,
      columns: {
        create: [
          { title: "To Do", position: 0 },
          { title: "In Progress", position: 1 },
          { title: "Done", position: 2 },
        ],
      },
    },
  })

  redirect(`/boards/${board.id}`)
}
