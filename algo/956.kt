// 다이나믹 프로그래밍

//import kotlin.math.*
//
//class Solution {
//    fun tallestBillboard(rods: IntArray): Int {
//        var dp = mutableMapOf<Int, Int>(0 to 0)
//
//        for (rod in rods) {
//            val newDp = dp.toMutableMap()
//            for ((diff, taller) in dp) {
//                newDp[diff + rod] = max(newDp.getOrDefault(diff + rod, 0), taller + rod)
//                newDp[abs(rod - diff)] = max(newDp.getOrDefault(abs(rod - diff), 0), max(taller - diff + rod, taller))
//            }
//            dp = newDp
//        }
//        return dp.getValue(0)
//    }
//}

// Meet in the middle

import kotlin.math.*

class Solution {
    fun getDp(rods: IntArray): Map<Int, Int> {
        val states = mutableSetOf<Pair<Int, Int>>(0 to 0)
        for (rod in rods) {
            val newStates = mutableSetOf<Pair<Int, Int>>()
            for ((left, right) in states) {
                newStates.add(left + rod to right)
                newStates.add(left to right + rod)
            }
            states.addAll(newStates)
        }
        var dp = mutableMapOf<Int, Int>()
        for ((left, right) in states) {
            dp[left - right] = left
        }
        return dp
    }

    fun tallestBillboard(rods: IntArray): Int {
        val half = rods.size / 2
        val firstHalf = getDp(rods.sliceArray(0 until half))
        val secondHalf = getDp(rods.sliceArray(half .. rods.lastIndex))

        var answer = 0
        for ((diff, left) in firstHalf) {
            if (secondHalf.containsKey(-diff)) {
                answer = max(answer, secondHalf.getValue(-diff) + left)
            }
        }
        return answer
    }
}

//val rods = intArrayOf(1, 2, 3, 6)
val rods = intArrayOf(1, 2, 3, 4, 5, 6)

println(Solution().tallestBillboard(rods))

//println(Solution().getDp(intArrayOf(1, 2)))
