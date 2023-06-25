import kotlin.math.max

class Solution {
    fun maxProfit(prices: IntArray, fee: Int): Int {
        val hold = IntArray(prices.size)
        val free = IntArray(prices.size)
        hold[0] = -prices[0]

        for (i in 1..prices.lastIndex) {
            hold[i] = max(hold[i - 1], free[i - 1] - prices[i])
            free[i] = max(free[i - 1], hold[i - 1] + prices[i] - fee)
        }
        return free[free.lastIndex]
    }
}
