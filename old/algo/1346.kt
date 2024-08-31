class Solution {
    fun search(arr: IntArray, l: Int, r: Int, target: Int): Int {
        var left = l
        var right = r
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (arr[mid] == target) {
                return mid
            } else if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return -1
    }

    fun checkIfExist(arr: IntArray): Boolean {
        arr.sort()
        for ((i, num) in arr.withIndex()) {
            val index = search(arr, 0, arr.lastIndex, num * 2)
            if (index != -1 && index != i) {
                return true
            }
        }
        return false
    }
}
