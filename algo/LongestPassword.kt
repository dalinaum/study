fun solution(S: String): Int {
    val passwords = S.split(" ")
    var maxLength = -1
    for (password in passwords) {
        val letterCount = password.count { it.isLetter() }
        val digitCount = password.count { it.isDigit() }
        if (letterCount + digitCount != password.length) {
            continue
        }
        if (letterCount % 2 == 0 && digitCount % 2 == 1) {
            maxLength = max(password.length, maxLength)
        }
    }
    return maxLength
}

val s = "test 5 a0A pass007 ?xy1"
println(solution(s))