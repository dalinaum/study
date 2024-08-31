class Solution {
    fun numberOfSteps(num: Int): Int {
        var n = num
        var count = 0
        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2
            } else {
                n --
            }
            count++
        }
        return count
    }
}

//val num = 14
//val num = 8
val num = 123
println(Solution().numberOfSteps(num))
