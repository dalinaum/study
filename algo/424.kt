class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        var left = 0
        val counts = mutableMapOf<Char, Int>()
        var start = 0
        var end = 0
        for ((right, ch) in s.withIndex()) {
            counts[ch] = counts.getOrDefault(ch, 0) + 1
            var maxCharLen = counts.entries.maxBy { it.value }.value
            var target = right - left + 1 - maxCharLen

            if (target > k) {
                val leftChar = s[left]
                counts[leftChar] = counts.getValue(leftChar) - 1
                left++
            }
            start = left
            end = right
        }
        return end - start + 1
    }
}

//val s = "ABAB"
//val k = 2

//val s = "AABABBA"
//val k = 1

val s = "AAAA"
val k = 2

println(Solution().characterReplacement(s, k))
