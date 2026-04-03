import { describe, it, expect, vi, beforeEach, afterEach } from "vitest"
import { render, screen } from "@testing-library/react"
import { isOverdue, TaskCard } from "@/components/task-card"

describe("isOverdue", () => {
  beforeEach(() => {
    vi.useFakeTimers()
    vi.setSystemTime(new Date(2026, 3, 3)) // 2026-04-03
  })

  afterEach(() => {
    vi.useRealTimers()
  })

  it("returns false for null dueDate", () => {
    expect(isOverdue(null)).toBe(false)
  })

  it("returns false for future date", () => {
    expect(isOverdue(new Date(2026, 3, 10))).toBe(false)
  })

  it("returns true for past date", () => {
    expect(isOverdue(new Date(2026, 3, 1))).toBe(true)
  })

  it("returns false for today", () => {
    expect(isOverdue(new Date(2026, 3, 3))).toBe(false)
  })

  it("returns true for yesterday", () => {
    expect(isOverdue(new Date(2026, 3, 2))).toBe(true)
  })
})

describe("TaskCard", () => {
  beforeEach(() => {
    vi.useFakeTimers()
    vi.setSystemTime(new Date(2026, 3, 3))
  })

  afterEach(() => {
    vi.useRealTimers()
  })

  it("renders task title", () => {
    render(
      <TaskCard
        task={{ id: "1", title: "테스트 태스크", priority: "MEDIUM", dueDate: null }}
      />
    )
    expect(screen.getByText("테스트 태스크")).toBeInTheDocument()
  })

  it("renders priority badge", () => {
    render(
      <TaskCard
        task={{ id: "1", title: "태스크", priority: "HIGH", dueDate: null }}
      />
    )
    expect(screen.getByText("높음")).toBeInTheDocument()
  })

  it("renders due date for non-overdue task", () => {
    render(
      <TaskCard
        task={{ id: "1", title: "태스크", priority: "MEDIUM", dueDate: new Date(2026, 3, 10) }}
      />
    )
    expect(screen.getByText("2026. 4. 10.")).toBeInTheDocument()
  })

  it("renders overdue indicator for past due date", () => {
    render(
      <TaskCard
        task={{ id: "1", title: "태스크", priority: "MEDIUM", dueDate: new Date(2026, 3, 1) }}
      />
    )
    expect(screen.getByText("2일 전에 기한 초과")).toBeInTheDocument()
    expect(screen.getByLabelText("기한 초과")).toBeInTheDocument()
  })

  it("applies red border for overdue task", () => {
    const { container } = render(
      <TaskCard
        task={{ id: "1", title: "태스크", priority: "MEDIUM", dueDate: new Date(2026, 2, 31) }}
      />
    )
    const card = container.firstElementChild!
    expect(card.className).toContain("border-red-500")
  })

  it("does not apply red border for non-overdue task", () => {
    const { container } = render(
      <TaskCard
        task={{ id: "1", title: "태스크", priority: "MEDIUM", dueDate: new Date(2026, 3, 10) }}
      />
    )
    const card = container.firstElementChild!
    expect(card.className).not.toContain("border-red-500")
  })
})
