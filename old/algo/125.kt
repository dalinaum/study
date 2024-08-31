class Solution {
    fun isPalindrome(s: String): Boolean {
        val list = s.toLowerCase().filter { it.isLetterOrDigit() }.toMutableList()
        while (list.size > 1) {
            val first = list.removeAt(0)
            val last = list.removeAt(list.lastIndex)
            if (first != last) {
                return false
            }
        }
        return true
    }
}

// LeetCode가 lowerCase()와 removeFirst(), removeLast() 지원 안함.
