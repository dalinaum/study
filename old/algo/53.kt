// 일반적인 풀이

//import kotlin.math.max
//
//class Solution {
//    fun maxSubArray(nums: IntArray): Int {
//        var maxValue = nums[0]
//        for (i in 1..nums.lastIndex) {
//            nums[i] += if (nums[i - 1] < 0) 0 else nums[i - 1]
//            maxValue = max(maxValue, nums[i])
//        }
//        return maxValue
//    }
//}

// 카데인 알고리즘

import kotlin.math.max

class Solution {
    fun maxSubArray(nums: IntArray): Int {
        var currentMax = nums[0]
        var bestMax = currentMax
        for (i in 1..nums.lastIndex) {
            currentMax = max(nums[i], currentMax + nums[i])
            bestMax = max(bestMax, currentMax)
        }
        return bestMax
    }
}
