class Solution {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        fun visit(start: Int, sum: Int, path: List<Int>) {
            if (sum == target) {
                result.add(path)
                return
            } else if (sum > target) {
                return
            }
            for (i in start..candidates.lastIndex) {
                val candidate = candidates[i]
                visit(i, sum + candidate, path + candidate)
            }
        }
        visit(0, 0, listOf())
        return result
    }
}

//val candidates = intArrayOf(2,3,6,7)
//val target = 7

//val candidates = intArrayOf(2,3,5)
//val target = 8

val candidates = intArrayOf(2)
val target = 1
println(Solution().combinationSum(candidates, target))
