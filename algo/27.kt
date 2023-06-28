class Solution {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var skipped = 0
        for ((i, num) in nums.withIndex()) {
            if (num == `val`) {
                skipped++
            } else {
                nums[i - skipped] = nums[i]
            }
        }
        return nums.size - skipped
    }
}
