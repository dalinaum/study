//class Solution {
//    fun search(nums: IntArray, target: Int): Int {
//        var minIndex = 0
//        var minValue = nums[0]
//
//        for (i in 1..nums.lastIndex) {
//            if (nums[i] < minValue) {
//                minIndex = i
//                minValue = nums[i]
//            }
//        }
//        val sorted = nums.slice(minIndex..nums.lastIndex) + nums.slice(0 until minIndex)
//        var left = 0
//        var right = sorted.lastIndex
//
//        while (left <= right) {
//            val mid = left + ((right - left) / 2)
//            val midValue = sorted[mid]
//            if (midValue == target) {
//                return (mid + minIndex) % nums.size
//            } else if (midValue > target) {
//                right = mid - 1
//            } else {
//                left = mid + 1
//            }
//        }
//        return -1
//    }
//}

class Solution {
    fun search(nums: IntArray, target: Int): Int {
        // 이진 탐색으로 제일 작은 값을 찾기
        var left = 0
        var right = nums.lastIndex
        while (left < right) {
            val mid = left + ((right - left) / 2)
            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        val pivot = left

        // 이진 탐색에 midPivot을 이용해서 소팅하지 않고 검색
        left = 0
        right = nums.lastIndex
        while (left <= right) {
            var mid = left + ((right - left) / 2)
            val midPivot = (mid + pivot) % nums.size
            if (nums[midPivot] < target) {
                left = mid + 1
            } else if (nums[midPivot] > target) {
                right = mid -1
            } else {
                return midPivot
            }
        }
        return -1
    }
}

//val nums = intArrayOf(4,5,6,7,0,1,2)
//val target = 0

//val nums = intArrayOf(4,5,6,7,0,1,2)
//val target = 3

//val nums = intArrayOf(1)
//val target = 0

//val nums = intArrayOf(1)
//val target = 1

val nums = intArrayOf(3,1)
val target = 3

Solution().search(nums, target)
