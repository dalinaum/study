class Solution {
    // LeetCode는 Char.digitToInt를 가지고 있지 않음.
    fun Char.digitToInt(): Int {
        return Character.digit(this, 10)
    }

    fun getSum(a: Int, b: Int): Int {
        // Int.toString(2)는 음수를 2의 보수로 바꾸지 않음.
        val binA = Integer.toBinaryString(a).padStart(32, '0')
        val binB = Integer.toBinaryString(b).padStart(32, '0')
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
        // String.toInt(2)와 Integer.parseInt는 음수가 담긴 2진 표현을 제대로 파싱하지 못함.
        return Integer.parseUnsignedInt(joined, 2)
    }
}

//val a = 1
//val b = 2

//val a = 2
//val b = 2

val a = -1
val b = -1

Solution().getSum(a, b)
