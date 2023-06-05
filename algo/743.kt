data class TimeAndNode(
    val time: Int,
    val node: Int
) : Comparable<TimeAndNode> {
    override fun compareTo(other: TimeAndNode): Int {
        return compareValues(this.time, other.time)
    }
}

class Solution {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val graph = mutableMapOf<Int, MutableList<TimeAndNode>>()
        for ((u, v, w) in times) {
            val nodes = graph.getOrDefault(u, mutableListOf())
            nodes.add(TimeAndNode(w, v))
            graph[u] = nodes
            graph.putIfAbsent(v, mutableListOf())
        }

        val queue = PriorityQueue<TimeAndNode>()
        queue.add(TimeAndNode(0, k))

        val nodeToDist = mutableMapOf<Int, Int>()

        while (queue.isNotEmpty()) {
            val (time, node) = queue.poll()
            if (!nodeToDist.contains(node)) {
                nodeToDist[node] = time

                for ((w, v) in graph.getValue(node)) {
                    queue.add(TimeAndNode(time + w, v))
                }
            }
        }
        if (nodeToDist.size != n) {
            return - 1
        }
        val longDistance = nodeToDist.maxBy { it.value }
        return longDistance.value // LeetCode에서는 null처리를 해야.
    }
}

//val times = arrayOf(intArrayOf(2, 1, 1), intArrayOf(2, 3, 1), intArrayOf(3, 4, 1))
//val n = 4
//val k = 2

//val times = arrayOf(intArrayOf(1, 2, 1))
//val n = 2
//val k = 1

val times = arrayOf(intArrayOf(1, 2, 1))
val n = 2
val k = 2

println(Solution().networkDelayTime(times, n, k))
