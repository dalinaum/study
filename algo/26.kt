class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        val seen = mutableSetOf<Int>()
        var skipped = 0
        for ((i, num) in nums.withIndex()) {
            if (num in seen) {
                skipped++
            } else {
                nums[i - skipped] = num
                seen.add(num)
            }
        }
        return nums.size - skipped
    }
}
