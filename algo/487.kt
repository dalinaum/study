import kotlin.math.max

class Solution {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var prev = -1
        var current = 0
        var longest = 0
        for ((i, num) in nums.withIndex()) {
            if (num == 1) {
                current++
                if (prev != -1) {
                    longest = max(longest, prev + current + 1)   
                } else {
                    longest = max(longest, current)
                }
            } else {
                prev = current
                current = 0
                longest = max(longest, prev + 1)
            }
        }
        return longest
    }
}
