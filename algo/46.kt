class Solution {
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        fun visit(candidates: List<Int>, path: List<Int>) {
            if (path.size == nums.size) {
                result.add(path)
                return
            }
            for (ch in candidates) {
                visit(candidates - ch, path + ch)
            }
        }

        visit(nums.toList(), listOf())
        return result
    }
}

val nums = intArrayOf(1, 2, 3)
println(Solution().permute(nums))
