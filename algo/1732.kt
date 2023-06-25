import kotlin.math.max

class Solution {
    fun largestAltitude(gain: IntArray): Int {
        var sum = 0
        var maxValue = 0
        for (g in gain) {
            sum += g
            maxValue = max(maxValue, sum)
        }
        return maxValue
    }
}
