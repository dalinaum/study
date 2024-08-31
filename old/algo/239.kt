// ArrayDeque는 타임 아웃. LinkedList로 변경.

// class Solution {
//     fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
//         val dq = mutableListOf<Int>()
//         val results = mutableListOf<Int>()
//         for ((i, v) in nums.withIndex()) {
//             while (dq.isNotEmpty() && nums[dq.last()] < v) {
//                 dq.removeAt(dq.lastIndex)
//                 // LeetCode는 removeFirst, removeLast가 안됨.
//                 // 버전이 낮기 때문
//             }
//             dq.add(i)

//             if (i < k - 1) continue

//             while (dq.first() <= i - k) {
//                 dq.removeAt(0)
//             }
//             results.add(nums[dq.first()])
//         }
//         return results.toIntArray()
//     }
// }

// 33.33% beats 나쁜 풀이.

class Solution {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val dq = mutableListOf<Int>()
        val results = IntArray(nums.size - k + 1)
        for ((i, v) in nums.withIndex()) {
            while (dq.isNotEmpty() && nums[dq.last()] < v) {
                dq.removeAt(dq.lastIndex)
            }
            dq.add(i)

            if (i < k - 1) continue

            while (dq.first() <= i - k) {
                dq.removeAt(0)
            }
            results[i - k + 1] = nums[dq.first()]
        }
        return results
    }
}

// 배열을 대신 사용해 봄. 별로 좋아지지 않음.