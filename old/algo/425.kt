class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        val counts = HashMap<Char, Int>()
        var left = 0
        var (start, end) = 0 to 0
        for ((right, v) in s.withIndex().map { (k, v) -> k + 1 to v }) {
            counts[v] = (counts[v] ?: 0) + 1
            val mostCommonLength = counts.entries.sortedByDescending { it.value }.take(1)[0].value

            if (right - left - mostCommonLength > k) {
                val left_ch = s[left]
                counts[left_ch] = (counts[left_ch] ?: 0) - 1
                left++
            }
            start = left
            end = right
        }
        return end - start
    }
}

val s = "AAABBC"
val k = 2
println(Solution().characterReplacement(s, k))