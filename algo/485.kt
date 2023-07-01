import kotlin.math.max

class Solution {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var consecutive = 0
        var longest = 0
        for (i in nums) {
            if (i == 1) {
                consecutive++
                longest = max(longest, consecutive)
            } else {
                consecutive = 0
            }
        }
        return longest
    }
}
