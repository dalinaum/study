class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val count = mutableMapOf<Char, Int>()
        for (ch in magazine) {
            count[ch] = count.getOrDefault(ch, 0) + 1
        }
        for (ch in ransomNote) {
            if (!count.containsKey(ch)) {
                return false
            }
            val c = count.getValue(ch)
            if (c < 1) {
                return false
            }
            count[ch] = c - 1
        }
        return true
    }
}

//val ransomNote = "a"
//val magazine = "b"

//val ransomNote = "aa"
//val magazine = "ab"

val ransomNote = "aa"
val magazine = "aab"

println(Solution().canConstruct(ransomNote, magazine))
