class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for ((x, y) in prerequisites) {
            val ys = graph.getOrDefault(x, mutableListOf())
            ys.add(y)
            graph[x] = ys
            graph.putIfAbsent(y, mutableListOf())
        }

        val traced = mutableSetOf<Int>()
        val visited = mutableSetOf<Int>()

        fun visit(i: Int): Boolean {
            if (traced.contains(i)) {
                return false
            }
            if (visited.contains(i)) {
                return true
            }
            traced.add(i)

            for (j in graph.getValue(i)) {
                if (!visit(j)) {
                    return false
                }
            }
            traced.remove(i)
            visited.add(i)
            return true
        }

        for (x in graph.keys) {
            if (!visit(x)) {
                return false
            }
        }

        return true
    }
}

val numCourses = 2
val prerequisites = arrayOf(intArrayOf(1, 0))
//val prerequisites = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))

println(Solution().canFinish(numCourses, prerequisites))
