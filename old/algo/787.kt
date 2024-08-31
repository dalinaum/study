data class DestinationAndPrice(
    val dest: Int,
    val price: Int
)

data class QueueItem(
    val price: Int,
    val dest: Int,
    val stop: Int
) : Comparable<QueueItem> {
    override fun compareTo(other: QueueItem): Int {
        return compareValues(this.price, other.price)
    }
}

data class PriceAndStop(
    val price: Int,
    val stop: Int
)

class Solution {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val graph = mutableMapOf<Int, MutableList<DestinationAndPrice>>()
        for ((from, to, price) in flights) {
            val value = graph.getOrDefault(from, mutableListOf())
            value.add(DestinationAndPrice(to, price))
            graph[from] = value
        }

        val prices = Array<PriceAndStop>(n) {
            PriceAndStop(Int.MAX_VALUE, 0)
        }

        val queue = PriorityQueue<QueueItem>()
        queue.add(QueueItem(0, src, 0))
        while (queue.isNotEmpty()) {
            val queueItem = queue.poll()
            if (queueItem.dest == dst) {
                return queueItem.price
            }
            if (queueItem.stop <= k) {
                val stop = queueItem.stop + 1
                for ((dest, price) in graph.getOrDefault(queueItem.dest, mutableListOf())) {
                    val newPrice = price + queueItem.price
                    val oldPrice = prices[dest]
                    if (oldPrice.price > newPrice || oldPrice.stop >= stop) {
                        prices[dest] = PriceAndStop(newPrice, stop)
                        queue.add(QueueItem(newPrice, dest, stop))
                    }
                }
            }
        }
        return -1
    }
}

//val n = 4
//val flights = arrayOf(
//    intArrayOf(0, 1, 100),
//    intArrayOf(1, 2, 100),
//    intArrayOf(2, 0, 100),
//    intArrayOf(1, 3, 600),
//    intArrayOf(2, 3, 200)
//)
//val src = 0
//val dst = 3
//val k = 1

//val n = 3
//val flights = arrayOf(intArrayOf(0,1,100),intArrayOf(1,2,100),intArrayOf(0,2,500))
//val src = 0
//val dst = 2
//val k = 1

//val n = 3
//val flights = arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500))
//val src = 0
//val dst = 2
//val k = 0

val n = 5
val flights = arrayOf(intArrayOf(1, 2, 4))
val src = 0
val dst = 4
val k = 2

println(Solution().findCheapestPrice(4, flights, src, dst, k))
