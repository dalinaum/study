"use server"

import { revalidatePath } from "next/cache"
import { prisma } from "@/lib/db"

export async function createColumn(data: {
  boardId: string
  title: string
  color: string
}) {
  const { boardId, title, color } = data

  if (!title || title.trim() === "") {
    return { error: "칼럼 이름을 입력해주세요." }
  }

  if (!boardId) {
    return { error: "보드 정보가 없습니다." }
  }

  const lastColumn = await prisma.column.findFirst({
    where: { boardId },
    orderBy: { position: "desc" },
    select: { position: true },
  })

  const position = lastColumn ? lastColumn.position + 1 : 0

  await prisma.column.create({
    data: {
      title: title.trim(),
      position,
      color,
      boardId,
    },
  })

  revalidatePath(`/boards/${boardId}`)

  return { success: true }
}
