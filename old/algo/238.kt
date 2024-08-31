class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        var p = 1
        var ans = IntArray(nums.size)
        ans[0] = p
        for (i in 0..nums.size - 2) {
            p *= nums[i]
            ans[i + 1] = p
        }

        p = 1
        for (i in nums.size - 1 downTo 1) {
            p *= nums[i]
            ans[i - 1] *= p
        }
        return ans
    }
}
