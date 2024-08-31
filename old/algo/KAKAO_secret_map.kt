fun solution(n: Int, map1: IntArray, map2: IntArray): Array<String> {
    return map1.mapIndexed { i, a -> map2[i] or a }
        .map { it.toString(2).padStart(n, '0').replace('1', '#').replace('0', ' ') }
        .toTypedArray()
}

val n = 5
val map1 = intArrayOf(9, 20, 28, 18, 11)
val map2 = intArrayOf(30, 1, 21, 17, 28)

val result = solution(n, map1, map2)
for (i in result) {
    println(i)
}
