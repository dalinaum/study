class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun visit(start: Int, path: List<Int>) {
            for (i in start - 1 until nums.size) {
                if (i == start - 1) {
                    result.add(path)
                } else {
                    visit(i + 1, path + nums[i])
                }
            }
        }
        visit(0, listOf())
        return result
    }
}

val nums = intArrayOf(1, 2, 3)
println(Solution().subsets(nums))
