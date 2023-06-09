import java.util.PriorityQueue

// 소트로 해본 버전

//class Solution {
//    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
//        val sorted = points.sortedBy { (it[0] * it[0]) + (it[1] * it[1]) }
//        return sorted.slice(0 until k).toTypedArray()
//    }
//}

// 우선순위 큐로 구현해본 버전

data class Point(
    val distance: Int,
    val x: Int,
    val y: Int
) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
        return compareValues(this.distance, other.distance)
    }
}

class Solution {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val heapq = PriorityQueue<Point>()
        for ((x, y) in points) {
            heapq.add(Point((x * x) + (y * y), x, y))
        }
        val result = Array<IntArray>(k) { IntArray(2) }
        for (i in 0 until k) {
            val (_, x, y) = heapq.poll()
            result[i][0] = x
            result[i][1] = y
        }
        return result
    }
}

//val points = arrayOf(intArrayOf(1,3),intArrayOf(-2,2))
//val k = 1

val points = arrayOf(intArrayOf(3, 3), intArrayOf(5, -1), intArrayOf(-2, 4))
val k = 2

val result = Solution().kClosest(points, k)
println(result[0].contentToString())
println(result[1].contentToString())
