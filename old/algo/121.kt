class Solution {
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = Int.MIN_VALUE
        var lowest = Int.MAX_VALUE

        for (price in prices) {
            lowest = min(lowest, price)
            maxProfit = max(maxProfit, price - lowest)
        }
        return maxProfit
    }
}
