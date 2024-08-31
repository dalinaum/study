class Solution {
    val dp = mutableMapOf<Int, Int>()
    fun climbStairs(n: Int): Int {
        if (n <= 2) {
            return n
        }
        if (dp.contains(n)) {
            return dp.getValue(n)
        }
        val result = climbStairs(n - 1) + climbStairs(n - 2)
        dp[n] = result
        return result
    }
}
