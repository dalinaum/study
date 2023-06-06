class Solution {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n == 1) {
            return listOf(0)
        }
        
        var remains = n
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for ((i, j) in edges) {
            val js = graph.getOrDefault(i, mutableListOf())
            js.add(j)
            graph[i] = js

            val `is` = graph.getOrDefault(j, mutableListOf())
            `is`.add(i)
            graph[j] = `is`
        }

        var leaves = mutableListOf<Int>()
        for ((key, value) in graph.entries) {
            if (value.size == 1) {
                leaves.add(key)
            }
        }

        while (remains > 2) {
            remains -= leaves.size
            val newLeaves = mutableListOf<Int>()
            for (leaf in leaves) {
                val neighbor = graph.getValue(leaf).removeAt(0)
                val neighborValues = graph.getValue(neighbor)
                neighborValues.remove(leaf)
                if (neighborValues.size == 1) {
                    newLeaves.add(neighbor)
                }
            }
            leaves = newLeaves
        }
        return leaves
    }
}

//val n = 4
//val edges = arrayOf(intArrayOf(1, 0), intArrayOf(1, 2), intArrayOf(1, 3))


val n = 6
val edges = arrayOf(intArrayOf(3, 0), intArrayOf(3, 1), intArrayOf(3, 2), intArrayOf(3, 4), intArrayOf(5, 4))

println(Solution().findMinHeightTrees(n, edges))
