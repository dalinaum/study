//class Solution {
//    fun maxProfit(prices: IntArray): Int {
//        var result = 0
//        for (i in 1..prices.lastIndex) {
//            if (prices[i - 1] < prices[i]) {
//                result += (prices[i] - prices[i - 1])
//            }
//        }
//        return result
//    }
//}

import kotlin.math.max

class Solution {
    fun maxProfit(prices: IntArray): Int {
        return (1..prices.lastIndex).map { max(prices[it] - prices[it - 1], 0) }.sum()
    }
}

val prices = intArrayOf(7, 1, 5, 3, 6, 4)

println(Solution().maxProfit(prices))
