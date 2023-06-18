class Solution {
    fun singleNumber(nums: IntArray): Int {
        var result = 0
        for (num in nums) {
            result = result xor num
        }
        return result
    }
}

//val nums = intArrayOf(2,2,1)

//var nums = intArrayOf(4,1,2,1,2)

var nums = intArrayOf(1)
println(Solution().singleNumber(nums))
