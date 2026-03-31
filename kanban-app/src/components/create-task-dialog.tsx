"use client"

import { useState, useTransition } from "react"
import { createTask } from "@/app/actions/task"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { PlusIcon } from "lucide-react"

export function CreateTaskDialog({ columnId }: { columnId: string }) {
  const [open, setOpen] = useState(false)
  const [error, setError] = useState<string | null>(null)
  const [isPending, startTransition] = useTransition()

  function handleSubmit(formData: FormData) {
    setError(null)
    startTransition(async () => {
      const result = await createTask(null, formData)
      if (result?.error) {
        setError(result.error)
      } else if (result?.success) {
        setOpen(false)
        setError(null)
      }
    })
  }

  return (
    <Dialog
      open={open}
      onOpenChange={(nextOpen) => {
        setOpen(nextOpen)
        if (nextOpen) setError(null)
      }}
    >
      <DialogTrigger
        render={<Button variant="ghost" size="sm" className="w-full" />}
      >
        <PlusIcon className="mr-1 h-4 w-4" />
        태스크 추가
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>태스크 추가</DialogTitle>
          <DialogDescription>
            새로운 태스크를 생성합니다.
          </DialogDescription>
        </DialogHeader>
        <form action={handleSubmit} className="grid gap-4">
          <input type="hidden" name="columnId" value={columnId} />
          <div className="grid gap-2">
            <Label htmlFor={`title-${columnId}`}>제목</Label>
            <Input
              id={`title-${columnId}`}
              name="title"
              placeholder="태스크 제목을 입력하세요"
              required
            />
          </div>
          <div className="grid gap-2">
            <Label htmlFor={`description-${columnId}`}>설명 (선택)</Label>
            <textarea
              id={`description-${columnId}`}
              name="description"
              placeholder="태스크 설명을 입력하세요"
              rows={3}
              className="w-full min-w-0 rounded-lg border border-input bg-transparent px-2.5 py-2 text-sm transition-colors outline-none placeholder:text-muted-foreground focus-visible:border-ring focus-visible:ring-3 focus-visible:ring-ring/50 dark:bg-input/30"
            />
          </div>
          <div className="grid gap-2">
            <Label htmlFor={`priority-${columnId}`}>우선순위</Label>
            <select
              id={`priority-${columnId}`}
              name="priority"
              defaultValue="MEDIUM"
              className="w-full min-w-0 rounded-lg border border-input bg-transparent px-2.5 py-2 text-sm transition-colors outline-none focus-visible:border-ring focus-visible:ring-3 focus-visible:ring-ring/50 dark:bg-input/30"
            >
              <option value="LOW">낮음</option>
              <option value="MEDIUM">중</option>
              <option value="HIGH">높음</option>
              <option value="URGENT">긴급</option>
            </select>
          </div>
          <div className="grid gap-2">
            <Label htmlFor={`dueDate-${columnId}`}>기한 (선택)</Label>
            <Input
              id={`dueDate-${columnId}`}
              name="dueDate"
              type="date"
            />
          </div>
          {error && (
            <p className="text-sm text-destructive">{error}</p>
          )}
          <DialogFooter>
            <Button type="submit" disabled={isPending}>
              {isPending ? "추가 중..." : "추가"}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  )
}
