// 다이나믹 프로그래밍 풀이

import kotlin.math.*

class Solution {
    fun tallestBillboard(rods: IntArray): Int {
        var dp = mutableMapOf<Int, Int>(0 to 0)

        for (rod in rods) {
            val newDp = dp.toMutableMap()
            for ((diff, taller) in dp) {
                newDp[diff + rod] = max(newDp.getOrDefault(diff + rod, 0), taller + rod)
                newDp[abs(rod - diff)] = max(newDp.getOrDefault(abs(rod - diff), 0), max(taller - diff + rod, taller))
            }
            dp = newDp
        }
        return dp.getValue(0)
    }
}
