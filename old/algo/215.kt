// 정렬로 풀이

// class Solution {
//     fun findKthLargest(nums: IntArray, k: Int): Int {
//         return nums.sorted()[nums.size - k]
//     }
// }

data class Num(val value: Int) : Comparable<Num> {
    override fun compareTo(other: Num): Int {
        return compareValues(other.value, this.value)
    }
}

class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val heapq = PriorityQueue<Num>()
        for (num in nums) {
            heapq.add(Num(num))
        }
        for (i in 1 until k) {
            heapq.poll()
        }
        return heapq.poll().value
    }
}

//val nums = intArrayOf(3,2,1,5,6,4)
//val k = 2

val nums = intArrayOf(3,2,3,1,2,4,5,5,6)
val k = 4

println(Solution().findKthLargest(nums, k))
