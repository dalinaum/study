import { describe, it, expect, vi, beforeEach } from "vitest"

vi.mock("next/cache", () => ({
  revalidatePath: vi.fn(),
}))

vi.mock("@/lib/db", () => ({
  prisma: {
    column: {
      findFirst: vi.fn(),
      create: vi.fn(),
    },
  },
}))

import { createColumn } from "@/app/actions/column"
import { prisma } from "@/lib/db"

const mockPrisma = vi.mocked(prisma)

describe("createColumn", () => {
  beforeEach(() => {
    vi.clearAllMocks()
  })

  it("returns error when title is empty", async () => {
    const result = await createColumn({ boardId: "b1", title: "", color: "#94a3b8" })
    expect(result).toEqual({ error: "칼럼 이름을 입력해주세요." })
  })

  it("returns error when title is whitespace", async () => {
    const result = await createColumn({ boardId: "b1", title: "   ", color: "#94a3b8" })
    expect(result).toEqual({ error: "칼럼 이름을 입력해주세요." })
  })

  it("returns error when boardId is empty", async () => {
    const result = await createColumn({ boardId: "", title: "칼럼", color: "#94a3b8" })
    expect(result).toEqual({ error: "보드 정보가 없습니다." })
  })

  it("creates column with position 0 when board has no columns", async () => {
    mockPrisma.column.findFirst.mockResolvedValue(null)
    mockPrisma.column.create.mockResolvedValue({} as never)

    const result = await createColumn({ boardId: "b1", title: "새 칼럼", color: "#ef4444" })

    expect(result).toEqual({ success: true })
    expect(mockPrisma.column.create).toHaveBeenCalledWith({
      data: {
        title: "새 칼럼",
        position: 0,
        color: "#ef4444",
        boardId: "b1",
      },
    })
  })

  it("creates column with next position", async () => {
    mockPrisma.column.findFirst.mockResolvedValue({ position: 4 } as never)
    mockPrisma.column.create.mockResolvedValue({} as never)

    const result = await createColumn({ boardId: "b1", title: "칼럼", color: "#94a3b8" })

    expect(result).toEqual({ success: true })
    expect(mockPrisma.column.create).toHaveBeenCalledWith({
      data: expect.objectContaining({ position: 5 }),
    })
  })

  it("trims title", async () => {
    mockPrisma.column.findFirst.mockResolvedValue(null)
    mockPrisma.column.create.mockResolvedValue({} as never)

    await createColumn({ boardId: "b1", title: "  칼럼  ", color: "#94a3b8" })

    expect(mockPrisma.column.create).toHaveBeenCalledWith({
      data: expect.objectContaining({ title: "칼럼" }),
    })
  })
})
