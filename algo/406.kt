data class People(
    val h: Int,
    val k: Int
) : Comparable<People> {
    override fun compareTo(other: People): Int {
        return compareValuesBy(
            this,
            other,
            {
                -it.h
            },
            {
                it.k
            }
        )
    }
}

class Solution {
    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        val heapQ = PriorityQueue<People>()
        for ((h, k) in people) {
            heapQ.add(People(h, k))
        }
        while (heapQ.isNotEmpty()) {
            val (h, k) = heapQ.poll()
            result.add(k, intArrayOf(h, k))
        }

        return result.toTypedArray()
    }
}

//val people = arrayOf(
//    intArrayOf(7, 0),
//    intArrayOf(4, 4),
//    intArrayOf(7, 1),
//    intArrayOf(5, 0),
//    intArrayOf(6, 1),
//    intArrayOf(5, 2)
//)

//val people = arrayOf(
//    intArrayOf(6, 0),
//    intArrayOf(5, 0),
//    intArrayOf(4, 0),
//    intArrayOf(3, 2),
//    intArrayOf(2, 2),
//    intArrayOf(1, 4)
//)

val people = arrayOf(
    intArrayOf(9, 0),
    intArrayOf(7, 0),
    intArrayOf(1, 9),
    intArrayOf(3, 0),
    intArrayOf(2, 7),
    intArrayOf(5, 3),
    intArrayOf(6, 0),
    intArrayOf(3, 4),
    intArrayOf(6, 2),
    intArrayOf(5, 2)
)
val result = Solution().reconstructQueue(people)

for (i in result) {
    println(i.contentToString())
}
