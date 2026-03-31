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

export function TaskCard({
  task,
  className,
}: {
  task: TaskData
  className?: string
}) {
  return (
    <div
      className={cn(
        "rounded-md border bg-background p-3 shadow-sm",
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
          <span className="text-xs text-muted-foreground">
            {new Date(task.dueDate).toLocaleDateString("ko-KR")}
          </span>
        )}
      </div>
    </div>
  )
}
