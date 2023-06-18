class Solution {
    fun hammingDistance(x: Int, y: Int): Int {
        var value = x xor y
        var count = 0
        while (value > 0) {
            if ((value and 1) == 1) count++
            value /= 2
        }
        return count
    }
}

//val x = 1
//val y = 4

val x = 3
val y = 1
println(Solution().hammingDistance(x, y))
