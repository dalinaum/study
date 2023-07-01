class Solution {
    fun heightChecker(heights: IntArray): Int {
        val list = heights.toList()
        return list.sorted().zip(list).count { (first, second) -> first != second }
    }
}
