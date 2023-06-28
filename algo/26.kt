// Set을 쓴 방법

// class Solution {
//     fun removeDuplicates(nums: IntArray): Int {
//         val seen = mutableSetOf<Int>()
//         var skipped = 0
//         for ((i, num) in nums.withIndex()) {
//             if (num in seen) {
//                 skipped++
//             } else {
//                 nums[i - skipped] = num
//                 seen.add(num)
//             }
//         }
//         return nums.size - skipped
//     }
// }

// 이전 값과 비교

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        var skipped = 0
        for ((i, num) in nums.withIndex()) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                skipped++
            } else {
                nums[i - skipped] = num
            }
        }
        return nums.size - skipped
    }
}
