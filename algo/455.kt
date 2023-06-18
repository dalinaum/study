// 두개의 포인터로 하나씩 맞춰가도 됨.

//class Solution {
//    fun findContentChildren(g: IntArray, s: IntArray): Int {
//        g.sort()
//        s.sort()
//
//        var i = 0
//        var j = 0
//        while (i <= g.lastIndex && j <= s.lastIndex) {
//            if (g[i] <= s[j]) {
//                i++
//            }
//            j++
//        }
//        return i
//    }
//}

// 이진 bisetRight를 이용해서 원하는 값보다 큰 인덱스를 찾고 찾은 사람 수와 비교해서 결과를 얻을 수 있음.
class Solution {
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        g.sort()
        s.sort()

        var result = 0
        for (i in s) {
            val index = bisectRight(g, i)
            if (index > result) {
                result++
            }
        }
        return result
    }

    fun bisectRight(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size

        while (left < right) {
            val mid = left + ((right - left) / 2)
            if (arr[mid] <= target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}

//val g = intArrayOf(1, 2, 3)
//val s = intArrayOf(1, 1)

val g = intArrayOf(1, 2)
val s = intArrayOf(1, 2, 3)

println(Solution().findContentChildren(g, s))


//val test = intArrayOf(1,1,1,2,2,2,3,3,3)
//println(Solution().bisectRight(test, 0))
