// 상향식 메모이제이션

//class Solution {
//    val fb = mutableListOf<Int>()
//    init {
//        fb.add(0)
//        fb.add(1)
//    }
//
//    fun fib(n: Int): Int {
//        for (i in 2..n) {
//            fb.add(fb[i -1] + fb[i - 2])
//        }
//        return fb[n]
//    }
//}

// 하향식 메모이제이션 하향식이 성능이 좀 더 낫네.
//class Solution {
//    val fb = mutableMapOf<Int, Int>()
//    init {
//        fb[0] = 0
//        fb[1] = 1
//    }
//
//
//    fun fib(n: Int): Int {
//        if (fb.contains(n)) {
//            return fb.getValue(n)
//        }
//        fb[n] = fib(n - 1) + fib(n - 2)
//        return fb.getValue(n)
//    }
//}
//val n = 2
//val n = 3

// 배열을 사용하지 않는 풀이
class Solution {

    fun fib(n: Int): Int {
        var x = 0
        var y = 1
        for (i in 0 until n) {
            var temp = x + y
            x = y
            y = temp
        }
        return x
    }
}
val n = 4
println(Solution().fib(n))
