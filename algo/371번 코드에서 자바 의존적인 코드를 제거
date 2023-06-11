class Solution {
    // LeetCode는 Char.digitToInt를 가지고 있지 않음.
    fun Char.digitToInt(): Int {
        return Character.digit(this, 10)
    }

    fun getSum(a: Int, b: Int): Int {
        val binA = a.toUInt().toString(2).padStart(32, '0')
        val binB = b.toUInt().toString(2).padStart(32, '0')
        val list = mutableListOf<Int>()
        var carry = 0

        for (i in 31 downTo 0) {
            val curA = binA[i].digitToInt()
            val curB = binB[i].digitToInt()
            val q1 = curA and curB
            val q2 = curA xor curB
            val q3 = q2 and carry
            val sum = q2 xor carry
            carry = q1 or q3
            list.add(0, sum)
        }
        val joined = list.joinToString(separator = "") { it.toString() }
        return joined.toUInt(2).toInt()
    }
}

//val a = 1
//val b = 2

//val a = 2
//val b = 2

val a = -1
val b = -1

Solution().getSum(a, b)
