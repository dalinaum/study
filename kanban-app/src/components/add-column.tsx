"use client"

import { useState, useTransition, useRef } from "react"
import { createColumn } from "@/app/actions/column"
import { cn } from "@/lib/utils"
import { PlusIcon } from "lucide-react"

const PRESET_COLORS = [
  "#ef4444",
  "#f97316",
  "#eab308",
  "#22c55e",
  "#3b82f6",
  "#8b5cf6",
  "#ec4899",
  "#94a3b8",
]

export function AddColumn({ boardId }: { boardId: string }) {
  const [isEditing, setIsEditing] = useState(false)
  const [title, setTitle] = useState("")
  const [selectedColor, setSelectedColor] = useState("#94a3b8")
  const [error, setError] = useState<string | null>(null)
  const [isPending, startTransition] = useTransition()
  const inputRef = useRef<HTMLInputElement>(null)

  function reset() {
    setIsEditing(false)
    setTitle("")
    setSelectedColor("#94a3b8")
    setError(null)
  }

  function handleOpen() {
    setIsEditing(true)
    setTimeout(() => {
      inputRef.current?.focus()
    }, 0)
  }

  function handleSubmit() {
    if (!title.trim()) {
      setError("칼럼 이름을 입력해주세요.")
      return
    }

    setError(null)
    startTransition(async () => {
      const result = await createColumn({
        boardId,
        title: title.trim(),
        color: selectedColor,
      })
      if (result?.error) {
        setError(result.error)
      } else if (result?.success) {
        reset()
      }
    })
  }

  function handleKeyDown(e: React.KeyboardEvent<HTMLInputElement>) {
    if (e.key === "Enter") {
      e.preventDefault()
      handleSubmit()
    } else if (e.key === "Escape") {
      reset()
    }
  }

  if (!isEditing) {
    return (
      <button
        type="button"
        onClick={handleOpen}
        className="flex min-w-64 shrink-0 items-center justify-center gap-2 rounded-lg border-2 border-dashed border-muted-foreground/30 bg-muted/30 p-4 text-sm text-muted-foreground transition-colors hover:border-muted-foreground/50 hover:bg-muted/50"
      >
        <PlusIcon className="h-4 w-4" />
        새 칼럼 추가
      </button>
    )
  }

  return (
    <div className="flex min-w-64 shrink-0 flex-col gap-3 rounded-lg border-2 border-dashed border-muted-foreground/30 bg-muted/30 p-4">
      <input
        ref={inputRef}
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        onKeyDown={handleKeyDown}
        placeholder="칼럼 이름"
        className="w-full rounded-md border border-input bg-transparent px-2.5 py-2 text-sm outline-none placeholder:text-muted-foreground focus-visible:border-ring focus-visible:ring-3 focus-visible:ring-ring/50"
      />
      <div className="flex items-center gap-1.5">
        {PRESET_COLORS.map((color) => (
          <button
            key={color}
            type="button"
            onClick={() => setSelectedColor(color)}
            className={cn(
              "h-6 w-6 rounded-full border-2 transition-transform hover:scale-110",
              selectedColor === color
                ? "border-foreground scale-110"
                : "border-transparent"
            )}
            style={{ backgroundColor: color }}
            aria-label={`색상 ${color}`}
          />
        ))}
      </div>
      {error && <p className="text-sm text-destructive">{error}</p>}
      <div className="flex gap-2">
        <button
          type="button"
          onClick={handleSubmit}
          disabled={isPending}
          className="flex-1 rounded-md bg-primary px-3 py-1.5 text-sm font-medium text-primary-foreground transition-colors hover:bg-primary/90 disabled:opacity-50"
        >
          {isPending ? "추가 중..." : "추가"}
        </button>
        <button
          type="button"
          onClick={reset}
          disabled={isPending}
          className="rounded-md px-3 py-1.5 text-sm text-muted-foreground transition-colors hover:bg-muted"
        >
          취소
        </button>
      </div>
    </div>
  )
}
