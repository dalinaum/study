// 처음 생각한 솔루션

//class Solution {
//    fun romanToInt(s: String): Int {
//        var result = 0
//        var skipNext = false
//        outer@for ((i, ch) in s.withIndex()) {
//            when {
//                skipNext -> {
//                    skipNext = false
//                    continue@outer
//                }
//
//                ch == 'I' -> {
//                    if (i != s.lastIndex && "VX".contains(s[i + 1])) {
//                        skipNext = true
//                        when (s[i + 1]) {
//                            'V' -> result += 4
//                            'X' -> result += 9
//                        }
//                    } else {
//                        result += 1
//                    }
//                }
//
//                ch == 'V' -> {
//                    result += 5
//                }
//
//                ch == 'X' -> {
//                    if (i != s.lastIndex && "LC".contains(s[i + 1])) {
//                        skipNext = true
//                        when (s[i + 1]) {
//                            'L' -> result += 40
//                            'C' -> result += 90
//                        }
//                    } else {
//                        result += 10
//                    }
//                }
//
//                ch == 'L' -> {
//                    result += 50
//                }
//
//                ch == 'C' -> {
//                    if (i != s.lastIndex && "DM".contains(s[i + 1])) {
//                        skipNext = true
//                        when (s[i + 1]) {
//                            'D' -> result += 400
//                            'M' -> result += 900
//                        }
//                    } else {
//                        result += 100
//                    }
//                }
//
//                ch == 'D' -> {
//                    result += 500
//                }
//
//                ch == 'M' -> {
//                    result += 1000
//                }
//            }
//        }
//        return result
//    }
//}

// 솔루션을 보고 수정한 풀이

class Solution {
    fun romanToInt(s: String): Int {
        val romanToNumber = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )
        var num = 0
        var lastNumber = -1
        for (ch in s) {
            val current = romanToNumber.getValue(ch)
            if (lastNumber != -1 && current > lastNumber) {
                num -= lastNumber * 2
            }
            num += current
            lastNumber = current
        }
        return num
    }
}

//val s = "III"
//val s = "LVIII"
val s = "MCMXCIV"
println(Solution().romanToInt(s))
