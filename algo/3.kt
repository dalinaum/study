import kotlin.math.max

class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var left = 0
        val seen = mutableMapOf<Char, Int>()
        var maxLength = Int.MIN_VALUE
        for ((right, ch) in s.withIndex()) {
            if (seen.contains(ch) && seen.getValue(ch) >= left) {
                left = seen.getValue(ch) + 1
            }
            maxLength = max(maxLength, right - left + 1)
            seen[ch] = right
        }
        return maxLength
    }
}

//val s = "abcabcbb"
//val s = "bbbbb"
val s = "pwwkew"
println(Solution().lengthOfLongestSubstring(s))
