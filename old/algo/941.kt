class Solution {
    fun validMountainArray(arr: IntArray): Boolean {
        var prev = -1
        var decreasing = false
        for ((i, num) in arr.withIndex()) {
            if (num == prev || num > prev && decreasing) {
                return false
            }
            if (num < prev) {
                if (i < 2) {
                    return false
                }
                if (!decreasing) {
                    decreasing = true
                }
            }
            prev = num
        }
        return decreasing
    }
}
