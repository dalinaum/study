class Solution {
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) {
            return listOf()
        }
        val result = mutableListOf<String>()
        val numberToLetters = mapOf(
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tvu",
            '9' to "wxyz"
        )

        fun visit(start: Int, path: String) {
            if (digits.length == path.length) {
                result.add(path)
                return
            }

            for (i in start..digits.lastIndex) {
                for (ch in numberToLetters.getValue(digits[i])) {
                    visit(i + 1, path + ch)
                }
            }
        }

        visit(0, "")
        return result
    }
}

//val digits = "23"
val digits = ""
//val digits = "2"
println(Solution().letterCombinations(digits))
