// class Solution {
//     fun threeSum(nums: IntArray): List<List<Int>> {
//         val nums = nums.sorted()
//         var result = mutableListOf<List<Int>>()

//         for (i in 0..nums.size - 3) {
//             if (i > 0 && nums[i - 1] == nums[i]) continue
//             for (j in i + 1..nums.size - 2) {
//                 if (j > i + 1 && nums[j - 1] == nums[j]) continue
//                 for (k in j + 1..nums.size -1) {
//                     if (k > j + 1 && nums[k - 1] == nums[k]) continue

//                     if (nums[i] + nums[j] + nums[k] == 0) {
//                         result.add(listOf(nums[i], nums[j], nums[k]))
//                     }
//                 }
//             }
//         }
//         return result
//     }
// }

// var nums = intArrayOf(-1, 0, 1, 2, -1, -4)
// Solution().threeSum(nums)

class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val nums = nums.sorted()
        var result = mutableListOf<List<Int>>()

        for (i in 0..nums.size - 3) {
            if (i > 0 && nums[i - 1] == nums[i]) continue
            var left = i + 1
            var right = nums.size - 1
            while (left < right) {
                val calculated = nums[i] + nums[left] + nums[right]
                if (calculated == 0) {
                    result.add(listOf(nums[i], nums[left], nums[right]))
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--
                    }
                    left++
                    right--
                } else if (calculated < 0) {
                    left++
                } else {
                    right--
                }
            }
        }
        return result
    }
}

var nums = intArrayOf(-1, 0, 1, 2, -1, -4)
Solution().threeSum(nums)
