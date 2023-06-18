// any를 사용한 버전.

//class Solution {
//    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
//        return matrix.any { it.contains(target) }
//    }
//}

// 포인터를 이용해 찾는 버전.
class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var row = 0
        var column = matrix[0].lastIndex

        while (row < matrix.size && column >= 0) {
            val value = matrix[row][column]
            if (value < target) {
                row++
            } else if (value > target) {
                column--
            } else {
                return true
            }
        }
        return false
    }
}

//val matrix = arrayOf(
//    intArrayOf(1, 4, 7, 11, 15),
//    intArrayOf(2, 5, 8, 12, 19),
//    intArrayOf(3, 6, 9, 16, 22),
//    intArrayOf(10, 13, 14, 17, 24),
//    intArrayOf(18, 21, 23, 26, 30)
//)
//val target = 5

val matrix = arrayOf(
    intArrayOf(1, 4, 7, 11, 15),
    intArrayOf(2, 5, 8, 12, 19),
    intArrayOf(3, 6, 9, 16, 22),
    intArrayOf(10, 13, 14, 17, 24),
    intArrayOf(18, 21, 23, 26, 30)
)
val target = 20

println(Solution().searchMatrix(matrix, target))
