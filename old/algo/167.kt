// 브루트 포스

//class Solution {
//    fun twoSum(numbers: IntArray, target: Int): IntArray {
//        for (l in 0 until numbers.lastIndex) {
//            for (r in l + 1..numbers.lastIndex) {
//                val sum = numbers[l] + numbers[r]
//                if (sum == target) {
//                    return intArrayOf(l + 1, r + 1)
//                } else if (sum > target) {
//                    continue
//                }
//            }
//        }
//        return intArrayOf()
//    }
//}

// 투 포인터
//class Solution {
//    fun twoSum(numbers: IntArray, target: Int): IntArray {
//        var l = 0
//        var r = numbers.lastIndex
//
//        while (l != r) {
//            val sum = numbers[l] + numbers[r]
//            if (sum == target) {
//                return intArrayOf(l + 1, r + 1)
//            } else if (sum > target) {
//                r--
//            } else {
//                l++
//            }
//        }
//        return intArrayOf()
//    }
//}


// 이진 탐색. 시작 범위를 유의해야.
class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        for ((i, number) in numbers.withIndex()) {
            var left = i + 1
            var right = numbers.lastIndex
            val complement = target - number
            while (left <= right) {
                val mid = left + ((right - left) / 2)
                val midValue = numbers[mid]
                if (midValue > complement) {
                    right = mid - 1
                } else if (midValue < complement) {
                    left = mid + 1
                } else {
                    return intArrayOf(i + 1, mid + 1)
                }
            }
        }
        return intArrayOf()
    }
}

//val numbers = intArrayOf(2, 7, 11, 15)
//val target = 9

//val numbers = intArrayOf(2,3,4)
//val target = 6

//val numbers = intArrayOf(-1, 0)
//val target = -1

val numbers = intArrayOf(1, 2, 3, 4, 4, 9, 56, 90)
val target = 8

println(Solution().twoSum(numbers, target).contentToString())
