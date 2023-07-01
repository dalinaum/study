class Solution {
    fun moveZeroes(nums: IntArray): Unit {
        var zeros = 0
        for ((i, num) in nums.withIndex()) {
            if (num == 0) {
                zeros++
            } else {
                nums[i - zeros] = num
            }
        }
        val lastIndex = nums.lastIndex
        for (i in lastIndex - zeros + 1 .. lastIndex) {
            nums[i] = 0
        }
    }
}
