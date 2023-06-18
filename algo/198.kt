import kotlin.math.max

class Solution {
    fun rob(nums: IntArray): Int {
        val dp = mutableMapOf<Int, Int>()
        fun rob(i: Int): Int {
            if (i < 0) {
                return 0
            }
            if (dp.contains(i)) {
                return dp.getValue(i)
            }
            val result = max(rob(i - 1), rob(i - 2) + nums[i])
            dp[i] = result
            return result
        }
        return rob(nums.lastIndex)
    }
}

//val nums = intArrayOf(1,2,3,1)
val nums = intArrayOf(2, 7, 9, 3, 1)
println(Solution().rob(nums))
