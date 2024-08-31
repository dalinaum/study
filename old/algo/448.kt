class Solution {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        var missing = (1..nums.size).toMutableSet()
        for (num in nums) {
            if (num in missing) {
                missing.remove(num)
            }
        }
        return missing.toList()
    }
}
