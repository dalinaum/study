import kotlin.math.*

class Solution {
    fun minCost(nums: IntArray, cost: IntArray): Long {
        val numAndCost = nums.zip(cost).sortedBy { it.first }

        val prefixCost = LongArray(nums.size)
        prefixCost[0] = numAndCost[0].second.toLong()
        for (i in 1..numAndCost.lastIndex) {
            prefixCost[i] = prefixCost[i - 1] + numAndCost[i].second
        }

        val first = numAndCost[0].first
        var costSum = 0L
        for (i in 1..numAndCost.lastIndex) {
            costSum += abs(first - numAndCost[i].first) * numAndCost[i].second.toLong()
        }
        var minCostSum = costSum

        for (i in 1..numAndCost.lastIndex) {
            val diff = abs(numAndCost[i].first - numAndCost[i - 1].first)
            costSum += prefixCost[i - 1] * diff
            costSum -= (prefixCost[prefixCost.lastIndex] - prefixCost[i - 1]) * diff
            minCostSum = min(minCostSum, costSum)
        }
        return minCostSum
    }
}
