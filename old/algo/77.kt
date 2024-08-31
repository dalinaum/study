class Solution {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun visit(i: Int, path: List<Int>) {
            if (path.size == k) {
                result.add(path)
                return
            }
            for (j in i + 1..n) {
                visit(j, path + j)
            }
        }

        visit(0, listOf())
        return result
    }
}

//val n = 4
//val k = 2
//val n = 1
//val k = 1
val n = 5
val k = 3
println(Solution().combine(n, k))
