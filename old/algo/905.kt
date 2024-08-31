class Solution {
    fun sortArrayByParity(nums: IntArray): IntArray {
        outer@for ((i, num) in nums.withIndex()) {
            if (num % 2 == 0) {
                continue
            }
            for (j in i + 1 .. nums.lastIndex) {
                if (nums[j] % 2 == 0) {
                    nums[i] = nums[j]
                    nums[j] = num
                    continue@outer
                }
            }
            break
        }
        return nums
    }
}
