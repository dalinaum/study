// 이진 탐색으로 구현. 단 정렬을 먼저해야 함.

//class Solution {
//    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
//        nums2.sort()
//        val set = mutableSetOf<Int>()
//        for (i in nums1) {
//            var left = 0
//            var right = nums2.lastIndex
//            while (left <= right) {
//                val mid = left + ((right - left) / 2)
//                if (nums2[mid] > i) {
//                    right = mid - 1
//                } else if (nums2[mid] < i) {
//                    left = mid + 1
//                } else {
//                    set.add(i)
//                    break
//                }
//            }
//        }
//        return set.toIntArray()
//    }
//}

class Solution {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        nums1.sort()
        nums2.sort()
        val set = mutableSetOf<Int>()
        var l1 = 0
        var l2 = 0

        while (l1 < nums1.size && l2 < nums2.size) {
            if (nums1[l1] < nums2[l2]) {
                l1++
            } else if (nums1[l1] > nums2[l2]) {
                l2++
            } else {
                set.add(nums1[l1])
                l1++
                l2++
            }
        }
        return set.toIntArray()
    }
}

val nums1 = intArrayOf(1, 2, 2, 1)
val nums2 = intArrayOf(2, 2)

//val nums1 = intArrayOf(4, 9, 5)
//val nums2 = intArrayOf(9, 4, 9, 8, 4)

println(Solution().intersection(nums1, nums2).contentToString())
