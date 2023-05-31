import kotlin.collections.maxBy

class Solution {
    fun find(s: String, left: Int, right: Int): String {
        var l = left
        var r = right
        while (l >= 0 && r <= s.length && s[l] == s[r - 1]) {
            l--
            r++
        }
        return s.substring(l + 1, r - 1)
    }

    fun longestPalindrome(s: String): String {
        var result = ""
        for (i in 0 until s.length) {
            result = arrayOf(
                result,
                find(s, i, i + 1),
                find(s, i, i + 2)
            ).maxBy { it.length } ?: "" // 코틀린 버전 -_-
        }
        return result
    }
}

Solution().longestPalindrome("cbbd")
