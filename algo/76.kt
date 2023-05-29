class Solution {
    fun minWindow(s: String, t: String): String {
        val needs = HashMap<Char, Int>()
        for (ch in t) {
            needs[ch] = needs.getOrDefault(ch, 0) + 1
        }
        var missing = t.length
        var (start, end) = 0 to 0

        var lp = 0
        for ((rp, ch) in s.withIndex().map { (key, value) -> (key + 1) to value}) {
            if ((needs[ch] ?: 0) > 0) missing--
            needs[ch] = (needs[ch] ?: 0) - 1

            if (missing == 0) {
                while (lp < rp && (needs[s[lp]] ?: 0) < 0) {
                    needs[s[lp]] = (needs[s[lp]] ?: 0) + 1
                    lp++
                }

                if (end == 0 || rp - lp < end - start) {
                    start = lp
                    end = rp
                }
            }
        }
        return s.substring(start, end)
    }
}

//val s = "ADOBECODEBANC"
//val t = "ABC"

//val s = "a"
//val t = "a"

val s = "a"
val t = "aa"
println(Solution().minWindow(s, t))