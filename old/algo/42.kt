// class Solution {
//     fun trap(heights: IntArray): Int {
//         var left = 0
//         var right = heights.size - 1

//         var maxLeftHeight = Int.MIN_VALUE
//         var maxRightHeight = Int.MIN_VALUE
//         var volume = 0

//         while (left < right) {
//             maxLeftHeight = max(heights[left], maxLeftHeight)
//             maxRightHeight = max(heights[right], maxRightHeight)

//             if (maxLeftHeight < maxRightHeight) {
//                 volume += maxLeftHeight - heights[left]
//                 left++
//             } else {
//                 volume += maxRightHeight - heights[right]
//                 right--
//             }
//         }
//         return volume
//     }
// }

// val heights = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
// Solution().trap(heights)

class Solution {
    fun trap(heights: IntArray): Int {
        var volume = 0
        var stack = mutableListOf<Int>()

        for ((i, height) in heights.withIndex()) {
            while (stack.isNotEmpty() && heights[stack[stack.lastIndex]] < height) {
                val bottom = stack.removeAt(stack.lastIndex)
                if (stack.isEmpty()) break
                val lastTop = stack[stack.lastIndex]
                val minTopHeight = min(heights[lastTop], height)
                val distance = i - lastTop - 1
                val diff = minTopHeight - heights[bottom]
                volume += diff * distance
            }
            stack.add(i)
        }

        return volume
    }
}

val heights = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
Solution().trap(heights)
