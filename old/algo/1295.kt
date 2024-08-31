class Solution {
    fun findNumbers(nums: IntArray): Int {
        return nums.map { it.toString().length }.filter { it % 2 == 0 }.count()
    }
}
