import kotlin.math.max

class Solution {
    fun longestArithSeqLength(nums: IntArray): Int {
        var maxLength = 0
        val dp = Array(nums.size) { mutableMapOf<Int, Int>() }
        for (right in 1..nums.lastIndex) {
            for (left in 0..right - 1) {
                val diff = nums[right] - nums[left]
                val newLength = dp[left].getOrDefault(diff, 1) + 1
                dp[right][diff] = newLength
                maxLength = max(maxLength, newLength)
            }
        }
        return maxLength
    }
}
