import kotlin.math.max

// 메모이제이션

//class Solution {
//    fun rob(nums: IntArray): Int {
//        val dp = mutableMapOf<Int, Int>()
//        fun rob(i: Int): Int {
//            if (i < 0) {
//                return 0
//            }
//            if (dp.contains(i)) {
//                return dp.getValue(i)
//            }
//            val result = max(rob(i - 1), rob(i - 2) + nums[i])
//            dp[i] = result
//            return result
//        }
//        return rob(nums.lastIndex)
//    }
//}

// 타블레이션

class Solution {
    fun rob(nums: IntArray): Int {
        val dp = mutableListOf<Int>()
        dp.add(nums[0])
        if (nums.size == 1) {
            return dp[0]
        }
        dp.add(max(nums[0], nums[1]))
        if (nums.size == 2) {
            return dp[1]
        }
        for (i in 2..nums.lastIndex) {
            dp.add(max(dp[i - 1], dp[i - 2] + nums[i]))
        }
        return dp[dp.lastIndex]
    }
}

//val nums = intArrayOf(1,2,3,1)
val nums = intArrayOf(2, 7, 9, 3, 1)
println(Solution().rob(nums))
