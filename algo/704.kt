class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            var mid = (right + left) / 2
            val midValue = nums[mid]
            if (midValue == target) {
                return mid
            } else if (midValue < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return -1
    }
}
