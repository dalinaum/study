import { cn } from "@/lib/utils"

const priorityStyles = {
  URGENT: "bg-red-100 text-red-700 dark:bg-red-900/30 dark:text-red-400",
  HIGH: "bg-orange-100 text-orange-700 dark:bg-orange-900/30 dark:text-orange-400",
  MEDIUM: "bg-yellow-100 text-yellow-700 dark:bg-yellow-900/30 dark:text-yellow-400",
  LOW: "bg-gray-100 text-gray-700 dark:bg-gray-900/30 dark:text-gray-400",
} as const

const priorityLabels = {
  URGENT: "긴급",
  HIGH: "높음",
  MEDIUM: "중",
  LOW: "낮음",
} as const

export interface TaskData {
  id: string
  title: string
  priority: "LOW" | "MEDIUM" | "HIGH" | "URGENT"
  dueDate: Date | null
}

/** 날짜만 비교하여 기한 초과 여부를 판단합니다 (시간 무시). */
export function isOverdue(dueDate: Date | null): boolean {
  if (!dueDate) return false
  const now = new Date()
  const todayStart = new Date(now.getFullYear(), now.getMonth(), now.getDate())
  const due = new Date(dueDate)
  const dueStart = new Date(due.getFullYear(), due.getMonth(), due.getDate())
  return dueStart < todayStart
}

/** 기한 초과된 일수를 기반으로 상대 시간 문자열을 반환합니다. */
function getOverdueText(dueDate: Date): string {
  const now = new Date()
  const todayStart = new Date(now.getFullYear(), now.getMonth(), now.getDate())
  const due = new Date(dueDate)
  const dueStart = new Date(due.getFullYear(), due.getMonth(), due.getDate())
  const diffMs = todayStart.getTime() - dueStart.getTime()
  const diffDays = Math.floor(diffMs / (1000 * 60 * 60 * 24))
  return `${diffDays}일 전에 기한 초과`
}

export function TaskCard({
  task,
  className,
}: {
  task: TaskData
  className?: string
}) {
  const overdue = isOverdue(task.dueDate)

  return (
    <div
      className={cn(
        "rounded-md border bg-background p-3 shadow-sm",
        overdue && "border-red-500",
        className
      )}
    >
      <p className="text-sm font-medium">{task.title}</p>
      <div className="mt-1 flex items-center justify-between">
        <span
          className={cn(
            "inline-block rounded px-1.5 py-0.5 text-xs font-medium",
            priorityStyles[task.priority]
          )}
        >
          {priorityLabels[task.priority]}
        </span>
        {task.dueDate && (
          <span
            className={cn(
              "text-xs",
              overdue ? "text-red-500" : "text-muted-foreground"
            )}
          >
            {overdue && (
              <span className="mr-1" aria-label="기한 초과">
                ⚠️
              </span>
            )}
            {overdue
              ? getOverdueText(task.dueDate)
              : new Date(task.dueDate).toLocaleDateString("ko-KR")}
          </span>
        )}
      </div>
    </div>
  )
}
