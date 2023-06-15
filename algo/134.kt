//  타임아웃으로 쓸 수 없는 부분

//class Solution {
//    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
//        for (i in gas.indices) {
//            var fuel = gas[i]
////            println(fuel)
//            var canTravelPosition = i
//            for (tempJ in i + 1..i + 1 + gas.lastIndex) {
//                val j = tempJ % gas.size
//                val prev = (tempJ + gas.size - 1) % gas.size
////                println("$i $j $fuel / gas: ${gas[j]} cost: ${cost[prev]}")
//                fuel -= cost[prev]
//                if (fuel < 0) {
//                    canTravelPosition = -1
//                    break
//                }
//                fuel += gas[j]
////                println("$i $j $fuel")
//            }
//            if (canTravelPosition >= 0) {
//                return canTravelPosition
//            }
//        }
//        return -1
//    }
//}

// 반드시 한개의 해답이 있다는 것을 이용한 꼼수

class Solution {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        if (gas.sum() < cost.sum()) {
            return -1
        }
        var fuel = 0
        var start = 0
        for (i in gas.indices) {
            if (fuel + gas[i] < cost[i]) {
                start = i + 1
                fuel = 0
            } else {
                fuel += gas[i] - cost[i]
            }
        }
        return start
    }
}

//val gas = intArrayOf(1, 2, 3, 4, 5)
//val cost = intArrayOf(3, 4, 5, 1, 2)

val gas = intArrayOf(2, 3, 4)
val cost = intArrayOf(3, 4, 3)

println(Solution().canCompleteCircuit(gas, cost))
