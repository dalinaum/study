"use server"

import { revalidatePath } from "next/cache"
import { prisma } from "@/lib/db"
import { Priority } from "@/generated/prisma/enums"

export async function createTask(prevState: unknown, formData: FormData) {
  const title = formData.get("title") as string | null
  const description = formData.get("description") as string | null
  const priority = formData.get("priority") as string | null
  const dueDate = formData.get("dueDate") as string | null
  const columnId = formData.get("columnId") as string | null

  if (!title || title.trim() === "") {
    return { error: "제목을 입력해주세요." }
  }

  if (!columnId) {
    return { error: "컬럼 정보가 없습니다." }
  }

  const lastTask = await prisma.task.findFirst({
    where: { columnId },
    orderBy: { position: "desc" },
    select: { position: true },
  })

  const position = lastTask ? lastTask.position + 1 : 0

  const column = await prisma.column.findUnique({
    where: { id: columnId },
    select: { boardId: true },
  })

  if (!column) {
    return { error: "컬럼을 찾을 수 없습니다." }
  }

  await prisma.task.create({
    data: {
      title: title.trim(),
      description: description?.trim() || null,
      priority: (priority as Priority) || Priority.MEDIUM,
      dueDate: dueDate ? new Date(dueDate) : null,
      position,
      columnId,
    },
  })

  revalidatePath(`/boards/${column.boardId}`)

  return { success: true }
}
