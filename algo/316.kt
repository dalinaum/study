// class Solution {
//     fun removeDuplicateLetters(s: String): String {
//         val sSet = s.toSet()
//         for (ch in sSet.sorted()) {
//             val suffix = s.substring(s.indexOf(ch))
//             if (sSet == suffix.toSet()) {
//                 return ch + removeDuplicateLetters(suffix.replace(ch.toString(), ""))
//             }
//         }
//         return ""
//     }
// }

// //val s = "bcabc"
// //val s = "bcdabc"
// val s = "cbacdcbc"

println(Solution().removeDuplicateLetters(s))

class Solution {
    fun removeDuplicateLetters(s: String): String {
        val seen = mutableSetOf<Char>()
        val counts = HashMap<Char, Int>()
        for (ch in s) {
            counts[ch] = counts.getOrDefault(ch, 0) + 1
        }

        val stack = mutableListOf<Char>()
        for (ch in s) {
            counts[ch] = counts.getOrDefault(ch, 0) - 1 // 디폴트 값이 나올 수 없음.
            if (seen.contains(ch)) continue

            while (stack.isNotEmpty() && stack[stack.lastIndex] > ch && counts.getOrDefault(stack[stack.lastIndex], 0) > 0) {
                val lastCh = stack.removeAt(stack.lastIndex)
                seen.remove(lastCh)
            }

            stack.add(ch)
            seen.add(ch)
        }
        return stack.joinToString("")
    }
}

//val s = "bcabc"
//val s = "bcdabc"
val s = "cbacdcbc"

println(Solution().removeDuplicateLetters(s))
