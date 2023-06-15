class Solution {
    fun compute(list1: List<Int>, list2: List<Int>, op: Char): List<Int> {
        val result = mutableListOf<Int>()
        for (l in list1) {
            for (r in list2) {
                result.add(
                    when (op) {
                        '+' -> l + r
                        '-' -> l - r
                        else -> l * r
                    }
                )
            }
        }
        return result
    }

    fun diffWaysToCompute(expression: String): List<Int> {
        if (expression.length == 0) {
            return listOf()
        }
        expression.toIntOrNull()?.let {
            return listOf(it)
        }
        val result = mutableListOf<Int>()
        for ((index, ch) in expression.withIndex()) {
            if ("-+*".contains(ch)) {
                val list1 = diffWaysToCompute(expression.substring(0, index))
                val list2 = diffWaysToCompute(expression.substring(index + 1))
                val computed = compute(list1, list2, ch)
                result.addAll(computed)
            }
        }
        return result
    }
}

//val expression = "2-1-1"
val expression = "2*3-4*5"

println(Solution().diffWaysToCompute(expression))
