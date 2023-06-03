data class Freq(
    val num: Int,
    val count: Int
) : Comparable<Freq> {
    override fun compareTo(other: Freq): Int {
        return compareValues(other.count, this.count)
    }
}

class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val freqs = mutableMapOf<Int, Int>()
        for (num in nums) {
            freqs[num] = freqs.getOrDefault(num, 0) + 1
        }

        val heapq = PriorityQueue<Freq>()
        for ((num, count) in freqs.entries) {
            heapq.add(Freq(num, count))
        }
        val result = IntArray(k)
        for (i in 0 until k) {
            result[i] = heapq.poll().num
        }
        return result
    }
}

val nums = intArrayOf(1, 1, 1, 2, 2, 3)
val k = 2
println(Solution().topKFrequent(nums, k).contentToString())
