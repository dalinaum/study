// 디코딩해서 해결.

//class Solution {
//    // you need treat n as an unsigned value
//    fun hammingWeight(n:Int):Int {
//        return n.toUInt().toString(2).count { it == '1' }
//    }
//}

// 하나 작은 값과 And
class Solution {
    // you need treat n as an unsigned value
    fun hammingWeight(n:Int):Int {
        var value = n
        var count = 0
        while (value != 0) {
            value = value and (value - 1)
            count++
        }
        return count
    }
}

println(Solution().hammingWeight(11))
println(Solution().hammingWeight(128))
println(Solution().hammingWeight(-3))
