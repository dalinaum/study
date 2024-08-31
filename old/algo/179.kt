class Solution {
    fun largestNumber(nums: IntArray): String {
        for (i in 1..nums.lastIndex) {
            var right = i
            var left = right - 1
            while (left >= 0 && hasToSwap(nums[left], nums[right])) {
                val temp = nums[left]
                nums[left] = nums[right]
                nums[right] = temp
                left--
                right--
            }
        }
        var result = nums.joinToString("")
        while (result.length > 1 && result[0] == '0')  {
            result = result.substring(1)
        }
        return result
    }

    fun hasToSwap(i: Int, j: Int): Boolean {
        return "$i$j" < "$j$i"
    }
}
