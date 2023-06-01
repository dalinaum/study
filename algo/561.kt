class Solution {
    fun arrayPairSum(nums: IntArray): Int {
        return nums.sorted().filterIndexed { i, v -> i % 2 == 0}.sum()
    }
}

val nums = intArrayOf(1,4,3,2)
println(Solution().arrayPairSum(nums))
