class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = mutableListOf(0)
        val result = IntArray(temperatures.size)
        for (i in 1..temperatures.size - 1) {
            while (stack.isNotEmpty() && temperatures[stack[stack.lastIndex]] < temperatures[i]) {
                val top = stack[stack.lastIndex]
                result[top] = i - top
                stack.removeAt(stack.lastIndex)
            }
            stack.add(i)
        }
        return result
    }
}

//val temperatures = intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)
//val temperatures = intArrayOf(30, 40, 50, 60)
val temperatures = intArrayOf(30, 60, 90)
println(Solution().dailyTemperatures(temperatures).contentToString())
