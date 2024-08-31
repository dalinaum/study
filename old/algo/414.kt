import kotlin.math.max

class Solution {
    fun thirdMax(nums: IntArray): Int {
        if (nums.size == 2) {
            return max(nums[0], nums[1])
        } else if (nums.size == 1) {
            return nums[0]
        }
        val sorted = nums.sorted().reversed()
        var rank = 1
        for ((i, num) in sorted.withIndex()) {
            if (i == 0 || sorted[i - 1] == num) {
                continue
            }
            rank++
            if (rank == 3) {
                return num
            }
        }
        return sorted[0]
    }
}
