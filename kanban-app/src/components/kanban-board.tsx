"use client"

import { useState, useCallback, useRef, useId, useEffect } from "react"
import {
  DndContext,
  DragOverlay,
  closestCorners,
  PointerSensor,
  useSensor,
  useSensors,
  type DragStartEvent,
  type DragOverEvent,
  type DragEndEvent,
} from "@dnd-kit/core"
import {
  SortableContext,
  verticalListSortingStrategy,
  arrayMove,
} from "@dnd-kit/sortable"
import { useDroppable } from "@dnd-kit/core"
import { SortableTask } from "@/components/sortable-task"
import { TaskCard, type TaskData, isOverdue } from "@/components/task-card"
import { CreateTaskDialog } from "@/components/create-task-dialog"
import { moveTask } from "@/app/actions/task"
import { cn } from "@/lib/utils"

interface ColumnData {
  id: string
  title: string
  color: string
  tasks: TaskData[]
}

interface BoardData {
  id: string
  title: string
  description: string | null
  columns: ColumnData[]
}

function DroppableColumn({
  column,
  children,
  taskCount,
  overdueCount,
}: {
  column: ColumnData
  children: React.ReactNode
  taskCount: number
  overdueCount: number
}) {
  const { setNodeRef, isOver } = useDroppable({ id: column.id })

  return (
    <div
      ref={setNodeRef}
      className={cn(
        "flex min-w-64 flex-1 flex-col gap-3 rounded-xl border bg-muted/50 p-4 transition-colors",
        isOver && "ring-2 ring-primary/50 bg-muted"
      )}
    >
      <div className="flex items-center gap-2">
        <span
          className="text-lg leading-none"
          style={{ color: column.color }}
        >
          ●
        </span>
        <h2 className="font-semibold">{column.title}</h2>
        <span className="text-sm text-muted-foreground">{taskCount}</span>
        {overdueCount > 0 && (
          <span className="inline-flex h-5 min-w-5 items-center justify-center rounded-full bg-red-500 px-1.5 text-xs font-medium text-white">
            {overdueCount}
          </span>
        )}
      </div>
      <div className="flex flex-1 flex-col gap-2">
        {taskCount === 0 && (
          <p className="py-8 text-center text-sm text-muted-foreground">
            태스크가 없습니다
          </p>
        )}
        {children}
      </div>
      <CreateTaskDialog columnId={column.id} />
    </div>
  )
}

function findColumnByTaskId(columns: ColumnData[], taskId: string) {
  return columns.find((col) => col.tasks.some((t) => t.id === taskId))
}

export function KanbanBoard({ board }: { board: BoardData }) {
  const dndId = useId()
  const [columns, setColumns] = useState<ColumnData[]>(board.columns)
  const [activeTask, setActiveTask] = useState<TaskData | null>(null)
  const snapshotRef = useRef<ColumnData[]>(board.columns)
  const columnsRef = useRef(columns)
  useEffect(() => {
    columnsRef.current = columns
  }, [columns])



  const sensors = useSensors(
    useSensor(PointerSensor, { activationConstraint: { distance: 5 } })
  )

  const handleDragStart = useCallback((event: DragStartEvent) => {
    const { active } = event
    const current = columnsRef.current
    const col = findColumnByTaskId(current, active.id as string)
    const task = col?.tasks.find((t) => t.id === active.id)
    if (task) {
      snapshotRef.current = current.map((c) => ({
        ...c,
        tasks: [...c.tasks],
      }))
      setActiveTask(task)
    }
  }, [])

  const handleDragOver = useCallback((event: DragOverEvent) => {
    const { active, over } = event
    if (!over) return

    const activeId = active.id as string
    const overId = over.id as string

    const current = columnsRef.current
    const activeCol = findColumnByTaskId(current, activeId)
    if (!activeCol) return

    // over가 칼럼 자체인 경우 (빈 칼럼에 드롭)
    const overCol =
      current.find((c) => c.id === overId) ??
      findColumnByTaskId(current, overId)
    if (!overCol) return

    if (activeCol.id === overCol.id) return

    setColumns((prev) => {
      const sourceCol = prev.find((c) => c.id === activeCol.id)!
      const destCol = prev.find((c) => c.id === overCol.id)!
      const task = sourceCol.tasks.find((t) => t.id === activeId)!

      const overIndex = destCol.tasks.findIndex((t) => t.id === overId)
      const insertIndex = overIndex >= 0 ? overIndex : destCol.tasks.length

      return prev.map((col) => {
        if (col.id === sourceCol.id) {
          return {
            ...col,
            tasks: col.tasks.filter((t) => t.id !== activeId),
          }
        }
        if (col.id === destCol.id) {
          const newTasks = [...col.tasks.filter((t) => t.id !== activeId)]
          newTasks.splice(insertIndex, 0, task)
          return { ...col, tasks: newTasks }
        }
        return col
      })
    })
  }, [])

  const handleDragEnd = useCallback(
    async (event: DragEndEvent) => {
      const { active, over } = event
      setActiveTask(null)

      if (!over) {
        setColumns(snapshotRef.current)
        return
      }

      const activeId = active.id as string
      const overId = over.id as string

      // 같은 칼럼 내 순서 변경
      const currentCol = findColumnByTaskId(columnsRef.current, activeId)
      if (currentCol) {
        const oldIndex = currentCol.tasks.findIndex((t) => t.id === activeId)
        const newIndex = currentCol.tasks.findIndex((t) => t.id === overId)

        if (oldIndex !== -1 && newIndex !== -1 && oldIndex !== newIndex) {
          setColumns((prev) =>
            prev.map((c) => {
              if (c.id !== currentCol.id) return c
              return { ...c, tasks: arrayMove(c.tasks, oldIndex, newIndex) }
            })
          )
        }
      }

      // 서버에 저장 — columnsRef로 최신 state 참조
      const finalColumns = columnsRef.current
      const targetCol = findColumnByTaskId(finalColumns, activeId)
      if (targetCol) {
        const taskIds = targetCol.tasks.map((t) => t.id)
        const result = await moveTask({
          taskId: activeId,
          targetColumnId: targetCol.id,
          taskIds,
        })
        if (result?.error) {
          setColumns(snapshotRef.current)
        }
      }
    },
    []
  )

  const handleDragCancel = useCallback(() => {
    setActiveTask(null)
    setColumns(snapshotRef.current)
  }, [])

  return (
    <DndContext
      id={dndId}
      sensors={sensors}
      collisionDetection={closestCorners}
      onDragStart={handleDragStart}
      onDragOver={handleDragOver}
      onDragEnd={handleDragEnd}
      onDragCancel={handleDragCancel}
    >
      <div className="flex gap-6 overflow-x-auto">
        {columns.map((column) => (
          <SortableContext
            key={column.id}
            items={column.tasks.map((t) => t.id)}
            strategy={verticalListSortingStrategy}
          >
            <DroppableColumn
              column={column}
              taskCount={column.tasks.length}
              overdueCount={
                column.tasks.filter((t) => isOverdue(t.dueDate)).length
              }
            >
              {column.tasks.map((task) => (
                <SortableTask key={task.id} task={task} />
              ))}
            </DroppableColumn>
          </SortableContext>
        ))}
      </div>
      <DragOverlay>
        {activeTask ? <TaskCard task={activeTask} /> : null}
      </DragOverlay>
    </DndContext>
  )
}
