class Solution {
    fun getAverages(nums: IntArray, k: Int): IntArray {
        val result = Array(nums.size) { -1 }.toIntArray()
        for (i in k..nums.lastIndex - k) {
            val window = nums.sliceArray(i - k..i + k)
            val sum = window.fold(0L) { acc, it -> acc + it }
            val avg = (sum / window.size).toInt()
            result[i] = avg
        }
        return result
    }
}
