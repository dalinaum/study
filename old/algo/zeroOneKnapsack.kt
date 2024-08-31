import kotlin.math.max

val cargo = arrayOf(
    intArrayOf(4, 12), intArrayOf(2, 1), intArrayOf(10, 4), intArrayOf(1, 1), intArrayOf(2, 2)
)

fun zeroOneKnapsack(cargo: Array<IntArray>): Int {
    val capiacity = 15
    val pack: Array<IntArray> = Array(cargo.size + 1) { _ -> IntArray(capiacity + 1) }

    for (i in 0..cargo.size) {
        for (j in 0..capiacity) {
            if (i == 0 || j == 0) {
                pack[i][j] = 0
            } else if (cargo[i - 1][1] <= j) {
                pack[i][j] = max(pack[i - 1][j], cargo[i - 1][0] + pack[i - 1][j - cargo[i - 1][1]])
            } else {
                pack[i][j] = pack[i - 1][j]
            }
        }
    }
    for (i in pack) {
        println("${i.contentToString()}")
    }
    return pack[cargo.size][capiacity]
}

println(zeroOneKnapsack(cargo))
