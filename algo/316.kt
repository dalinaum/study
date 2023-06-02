class Solution {
    fun removeDuplicateLetters(s: String): String {
        val sSet = s.toSet()
        for (ch in sSet.sorted()) {
            val suffix = s.substring(s.indexOf(ch))
            if (sSet == suffix.toSet()) {
                return ch + removeDuplicateLetters(suffix.replace(ch.toString(), ""))
            }
        }
        return ""
    }
}

//val s = "bcabc"
//val s = "bcdabc"
val s = "cbacdcbc"

println(Solution().removeDuplicateLetters(s))
