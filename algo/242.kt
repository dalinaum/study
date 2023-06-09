class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        return s.toMutableList().sorted() == t.toMutableList().sorted()
    }
}

val s = "anagram"
val t = "nagaram"

Solution().isAnagram(s, t)
