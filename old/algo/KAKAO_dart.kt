fun solution(game: String): Int {
    var num = 0
    var prevNum = 0
    var sum = 0
    for ((i, ch) in game.withIndex()) {
        if (ch.isDigit()) {
            if (i != 0 && !(game[i - 1].isDigit())) {
                sum += num
                prevNum = num
                num = 0
            }
            num = (num * 10) + ch.toString().toInt()
        }
        when (ch) {
            'S' -> Unit
            'D' -> num = num * num
            'T' -> num = num * num * num
            '*' -> {
                sum += prevNum
                num = num * 2
            }
            '#' -> {
                num *= -1
            }
            else -> Unit
        }
//        println("num: $num sum: $sum")
    }
    sum += num
    return sum
}

val game1 = "1S2D*3T"
println(solution(game1))

val game2 = "1D2S#10S"
println(solution(game2))

val game3 = "1D2S0T"
println(solution(game3))

val game4 = "1S*2T*3S"
println(solution(game4))

val game5 = "1D#2S*3S"
println(solution(game5))

val game6 = "1T2D3D#"
println(solution(game6))

val game7 = "1D2S3T*"
println(solution(game7))
