import { describe, it, expect, vi, beforeEach } from "vitest"

vi.mock("next/navigation", () => ({
  redirect: vi.fn(),
}))

vi.mock("@/lib/db", () => ({
  prisma: {
    board: {
      create: vi.fn(),
    },
  },
}))

import { createBoard } from "@/app/actions/board"
import { prisma } from "@/lib/db"
import { redirect } from "next/navigation"

const mockPrisma = vi.mocked(prisma)

function makeFormData(data: Record<string, string>): FormData {
  const fd = new FormData()
  for (const [key, value] of Object.entries(data)) {
    fd.set(key, value)
  }
  return fd
}

describe("createBoard", () => {
  beforeEach(() => {
    vi.clearAllMocks()
  })

  it("returns error when title is empty", async () => {
    const result = await createBoard(null, makeFormData({ title: "" }))
    expect(result).toEqual({ error: "제목을 입력해주세요." })
  })

  it("returns error when title is whitespace only", async () => {
    const result = await createBoard(null, makeFormData({ title: "   " }))
    expect(result).toEqual({ error: "제목을 입력해주세요." })
  })

  it("creates board with 3 default columns and redirects", async () => {
    mockPrisma.board.create.mockResolvedValue({ id: "board123" } as never)

    await createBoard(null, makeFormData({ title: "내 보드", description: "설명" }))

    expect(mockPrisma.board.create).toHaveBeenCalledWith({
      data: {
        title: "내 보드",
        description: "설명",
        columns: {
          create: [
            { title: "To Do", position: 0 },
            { title: "In Progress", position: 1 },
            { title: "Done", position: 2 },
          ],
        },
      },
    })
    expect(redirect).toHaveBeenCalledWith("/boards/board123")
  })

  it("trims title and description", async () => {
    mockPrisma.board.create.mockResolvedValue({ id: "b1" } as never)

    await createBoard(null, makeFormData({ title: "  보드  ", description: "  설명  " }))

    expect(mockPrisma.board.create).toHaveBeenCalledWith({
      data: expect.objectContaining({
        title: "보드",
        description: "설명",
      }),
    })
  })
})
