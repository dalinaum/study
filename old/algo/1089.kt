class Solution {
    fun duplicateZeros(arr: IntArray): Unit {
        var seenZero = false
        for (i in 0..arr.lastIndex - 1) {
            if (seenZero) {
                seenZero = false
                continue
            }
            if (arr[i] != 0) {
                continue
            }
            seenZero = true
            for (j in arr.lastIndex downTo i + 2) {
                arr[j] = arr[j - 1]
            }
            arr[i + 1] = 0
        }
    }
}
