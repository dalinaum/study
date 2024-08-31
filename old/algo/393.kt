class Solution {
    fun validUtf8(data: IntArray): Boolean {
        val binaryData = data.map { it.toString(2).padStart(8, '0') }
        var tailing = 0
        for (binary in binaryData) {
            if (tailing > 0) {
                if (!binary.startsWith("10")) {
                    return false
                }
                tailing--
                continue
            }

            if (binary.startsWith("110")) {
                tailing = 1
            } else if (binary.startsWith("1110")) {
                tailing = 2
            } else if (binary.startsWith("11110")) {
                tailing = 3
            } else if (binary.startsWith("1")) {
                return false
            }
        }
        return tailing == 0
    }
}

//val data = intArrayOf(197, 130, 1)

val data = intArrayOf(235,140,4)

println(Solution().validUtf8(data))
