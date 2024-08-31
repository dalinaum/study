// 리스트나 어레이로 바꾸고 소트하면 성능이 파이썬보다 떨어진다.
// class Solution {
//     fun isAnagram(s: String, t: String): Boolean {
//         return s.toMutableList().sorted() == t.toMutableList().sorted()
//     }
// }

// class Solution {
//     fun isAnagram(s: String, t: String): Boolean {
//         return s.toCharArray().sorted() == t.toCharArray().sorted()
//     }
// }

class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        val map = hashMapOf<Char, Int>()
        for (ch in s) {
            map[ch] = map.getOrDefault(ch, 0) + 1
        }
        for (ch in t) {
            map[ch] = map.getOrDefault(ch, 0) - 1
        }
        return map.count { (ch, count) -> count != 0 } == 0
    }
}

val s = "anagram"
val t = "nagaram"

Solution().isAnagram(s, t)
