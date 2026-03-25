"use client"

import { useActionState } from "react"
import { createBoard } from "@/app/actions/board"
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

export function CreateBoardDialog() {
  const [state, formAction, isPending] = useActionState(createBoard, null)

  return (
    <Dialog>
      <DialogTrigger render={<Button />}>새 보드 만들기</DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>새 보드 만들기</DialogTitle>
          <DialogDescription>
            보드를 생성하면 기본으로 To Do, In Progress, Done 컬럼이 만들어집니다.
          </DialogDescription>
        </DialogHeader>
        <form action={formAction} className="grid gap-4">
          <div className="grid gap-2">
            <Label htmlFor="title">제목</Label>
            <Input
              id="title"
              name="title"
              placeholder="보드 제목을 입력하세요"
              required
            />
          </div>
          <div className="grid gap-2">
            <Label htmlFor="description">설명 (선택)</Label>
            <textarea
              id="description"
              name="description"
              placeholder="보드 설명을 입력하세요"
              rows={3}
              className="w-full min-w-0 rounded-lg border border-input bg-transparent px-2.5 py-2 text-sm transition-colors outline-none placeholder:text-muted-foreground focus-visible:border-ring focus-visible:ring-3 focus-visible:ring-ring/50 dark:bg-input/30"
            />
          </div>
          {state?.error && (
            <p className="text-sm text-destructive">{state.error}</p>
          )}
          <DialogFooter>
            <Button type="submit" disabled={isPending}>
              {isPending ? "생성 중..." : "만들기"}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  )
}
