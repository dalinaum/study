import kotlin.math.max

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val sorted = intervals.sortedBy { it[0] }
        val result = mutableListOf<IntArray>()
        for (pair in sorted) {
            if (result.isNotEmpty() && result[result.lastIndex][1] >= pair[0]) {
                result[result.lastIndex] = intArrayOf(result[result.lastIndex][0], max(result[result.lastIndex][1], pair[1]))
            } else {
                result.add(pair)
            }
        }
        return result.toTypedArray()
    }
}

//val intervals = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))

val intervals = arrayOf(intArrayOf(1,4),intArrayOf(4,5))

val merged = Solution().merge(intervals)
for (pair in merged) {
    println(pair.contentToString())
}
