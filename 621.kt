import java.util.PriorityQueue

data class Item(
    val count: Int,
    val task: Char
) : Comparable<Item> {
    override fun compareTo(other: Item): Int {
        return compareValues(other.count, count)
    }
}

class Solution {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val counts = HashMap<Char, Int>()
        for (task in tasks) {
            counts[task] = counts.getOrDefault(task, 0) + 1
        }
        val priorityQueye = PriorityQueue<Item>()
        for ((task, count) in counts.entries) {
            priorityQueye.add(Item(count, task))
        }
        var result = 0
        while (true) {
            var i = 0
            val mostCommon = mutableListOf<Item>()
            while (priorityQueye.isNotEmpty() && i < n + 1) {
                val item = priorityQueye.poll()
                mostCommon.add(item)
                i++
            }
            for ((count, task) in mostCommon) {
                if (count > 1) {
                    priorityQueye.add(Item(count - 1, task))
                }
            }
            if (priorityQueye.isEmpty()) {
                result += mostCommon.size
                break
            }
            result += n + 1
        }
        return result
    }
}

//val tasks = charArrayOf('A', 'A', 'A', 'B', 'B', 'B')
//val n = 2

//val tasks = charArrayOf('A', 'A', 'A', 'B', 'B', 'B')
//val n = 0

val tasks = charArrayOf('A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G')
val n = 2
println(Solution().leastInterval(tasks, n))
