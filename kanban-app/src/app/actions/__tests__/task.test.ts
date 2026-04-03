import { describe, it, expect, vi, beforeEach } from "vitest"

vi.mock("next/cache", () => ({
  revalidatePath: vi.fn(),
}))

vi.mock("@/lib/db", () => ({
  prisma: {
    task: {
      findFirst: vi.fn(),
      findUnique: vi.fn(),
      create: vi.fn(),
      update: vi.fn(),
    },
    column: {
      findUnique: vi.fn(),
    },
    $transaction: vi.fn(),
  },
}))

vi.mock("@/generated/prisma/enums", () => ({
  Priority: { LOW: "LOW", MEDIUM: "MEDIUM", HIGH: "HIGH", URGENT: "URGENT" },
}))

import { createTask, moveTask } from "@/app/actions/task"
import { prisma } from "@/lib/db"

const mockPrisma = vi.mocked(prisma)

function makeFormData(data: Record<string, string>): FormData {
  const fd = new FormData()
  for (const [key, value] of Object.entries(data)) {
    fd.set(key, value)
  }
  return fd
}

describe("createTask", () => {
  beforeEach(() => {
    vi.clearAllMocks()
  })

  it("returns error when title is empty", async () => {
    const result = await createTask(null, makeFormData({ title: "", columnId: "col1" }))
    expect(result).toEqual({ error: "제목을 입력해주세요." })
  })

  it("returns error when title is missing", async () => {
    const result = await createTask(null, makeFormData({ columnId: "col1" }))
    expect(result).toEqual({ error: "제목을 입력해주세요." })
  })

  it("returns error when columnId is missing", async () => {
    const result = await createTask(null, makeFormData({ title: "태스크" }))
    expect(result).toEqual({ error: "컬럼 정보가 없습니다." })
  })

  it("returns error when column not found", async () => {
    mockPrisma.task.findFirst.mockResolvedValue(null)
    mockPrisma.column.findUnique.mockResolvedValue(null)

    const result = await createTask(
      null,
      makeFormData({ title: "태스크", columnId: "col1" })
    )
    expect(result).toEqual({ error: "컬럼을 찾을 수 없습니다." })
  })

  it("creates task with position 0 when column is empty", async () => {
    mockPrisma.task.findFirst.mockResolvedValue(null)
    mockPrisma.column.findUnique.mockResolvedValue({ boardId: "board1" } as never)
    mockPrisma.task.create.mockResolvedValue({} as never)

    const result = await createTask(
      null,
      makeFormData({ title: "태스크", columnId: "col1", priority: "HIGH" })
    )

    expect(result).toEqual({ success: true })
    expect(mockPrisma.task.create).toHaveBeenCalledWith({
      data: expect.objectContaining({
        title: "태스크",
        position: 0,
        priority: "HIGH",
        columnId: "col1",
      }),
    })
  })

  it("creates task with next position", async () => {
    mockPrisma.task.findFirst.mockResolvedValue({ position: 2 } as never)
    mockPrisma.column.findUnique.mockResolvedValue({ boardId: "board1" } as never)
    mockPrisma.task.create.mockResolvedValue({} as never)

    const result = await createTask(
      null,
      makeFormData({ title: "태스크", columnId: "col1" })
    )

    expect(result).toEqual({ success: true })
    expect(mockPrisma.task.create).toHaveBeenCalledWith({
      data: expect.objectContaining({ position: 3 }),
    })
  })
})

describe("moveTask", () => {
  beforeEach(() => {
    vi.clearAllMocks()
  })

  it("returns error when task not found", async () => {
    mockPrisma.task.findUnique.mockResolvedValue(null)

    const result = await moveTask({
      taskId: "t1",
      targetColumnId: "col2",
      taskIds: ["t1"],
    })
    expect(result).toEqual({ error: "태스크를 찾을 수 없습니다." })
  })

  it("moves task with transaction", async () => {
    mockPrisma.task.findUnique.mockResolvedValue({
      columnId: "col1",
      column: { boardId: "board1" },
    } as never)
    mockPrisma.$transaction.mockResolvedValue([])

    const result = await moveTask({
      taskId: "t1",
      targetColumnId: "col2",
      taskIds: ["t1", "t2"],
    })

    expect(result).toEqual({ success: true })
    expect(mockPrisma.$transaction).toHaveBeenCalledTimes(1)
  })
})
