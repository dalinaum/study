// 다이나믹 프로그래밍으로 풀이. 이전에 본 값의 내용을 기억할 필요가 없어 map을 쓰지 않음.

//class Solution {
//    fun majorityElement(nums: IntArray): Int {
//        val seen = mutableSetOf<Int>()
//        for (num in nums) {
//            if (seen.contains(num)) {
//                continue
//            }
//            if (nums.count { it == num } > (nums.size / 2)) {
//                return num
//            }
//            seen.add(num)
//        }
//        return -1
//    }
//}

// 분할정복으로도 풀수 있는데 효과적이지는 않음.

//class Solution {
//    fun majorityElement(nums: IntArray): Int {
//        if (nums.size == 1) {
//            return nums[0]
//        }
//        val mid = nums.size / 2
//        val a = majorityElement(nums.sliceArray(0 until mid))
//        val b = majorityElement(nums.sliceArray(mid until nums.size))
//        return if (nums.count { it==a } > mid) a else b
//    }
//}

// Sort로 풀수 있음.

class Solution {
    fun majorityElement(nums: IntArray): Int {
        return nums.sorted()[nums.size / 2]
    }
}

val nums = intArrayOf(3, 2, 3)

//val nums = intArrayOf(2, 2, 1, 1, 1, 2, 2)
println(Solution().majorityElement(nums))
