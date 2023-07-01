import kotlin.math.max

class Solution {
    fun replaceElements(arr: IntArray): IntArray {
        for ((i, num) in arr.withIndex()) {
            if (i == arr.lastIndex) {
                arr[arr.lastIndex] = -1        
                break
            }
            var maxNum = Int.MIN_VALUE
            for (j in i + 1 .. arr.lastIndex) {
                maxNum = max(maxNum, arr[j])
            }
            arr[i] = maxNum
        }
        return arr
    }
}
