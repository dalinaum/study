#%%
import java.util.PriorityQueue

data class Item(
    val count: Int,
    val task: Char
) : Comparable<Item> {
    override fun compareTo(other: Item): Int {
        return compareValues(other.count, count)
    }
}

class Solution {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val counts = HashMap<Char, Int>()
        for (task in tasks) {
            counts[task] = counts.getOrDefault(task, 0) + 1
        }
        val priorityQueye = PriorityQueue<Item>()
        for ((task, count) in counts.entries) {
            priorityQueye.add(Item(count, task))
        }
        var result = 0
        while (true) {
            var i = 0
            val mostCommon = mutableListOf<Item>()
            while (priorityQueye.isNotEmpty() && i < n + 1) {
                val item = priorityQueye.poll()
                mostCommon.add(item)
                i++
            }
            for ((count, task) in mostCommon) {
                if (count > 1) {
                    priorityQueye.add(Item(count - 1, task))
                }
            }
            if (priorityQueye.isEmpty()) {
                result += mostCommon.size
                break
            }
            result += n + 1
        }
        return result
    }
}

//val tasks = charArrayOf('A', 'A', 'A', 'B', 'B', 'B')
//val n = 2

//val tasks = charArrayOf('A', 'A', 'A', 'B', 'B', 'B')
//val n = 0

val tasks = charArrayOf('A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G')
val n = 2
println(Solution().leastInterval(tasks, n))
#%%
//  타임아웃으로 쓸 수 없는 부분

//class Solution {
//    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
//        for (i in gas.indices) {
//            var fuel = gas[i]
////            println(fuel)
//            var canTravelPosition = i
//            for (tempJ in i + 1..i + 1 + gas.lastIndex) {
//                val j = tempJ % gas.size
//                val prev = (tempJ + gas.size - 1) % gas.size
////                println("$i $j $fuel / gas: ${gas[j]} cost: ${cost[prev]}")
//                fuel -= cost[prev]
//                if (fuel < 0) {
//                    canTravelPosition = -1
//                    break
//                }
//                fuel += gas[j]
////                println("$i $j $fuel")
//            }
//            if (canTravelPosition >= 0) {
//                return canTravelPosition
//            }
//        }
//        return -1
//    }
//}

class Solution {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        if (gas.sum() < cost.sum()) {
            return -1
        }
        var fuel = 0
        var start = 0
        for (i in gas.indices) {
            if (fuel + gas[i] < cost[i]) {
                start = i + 1
                fuel = 0
            } else {
                fuel += gas[i] - cost[i]
            }
        }
        return start
    }
}

//val gas = intArrayOf(1, 2, 3, 4, 5)
//val cost = intArrayOf(3, 4, 5, 1, 2)

val gas = intArrayOf(2, 3, 4)
val cost = intArrayOf(3, 4, 3)

println(Solution().canCompleteCircuit(gas, cost))
#%%
// 두개의 포인터로 하나씩 맞춰가도 됨.

//class Solution {
//    fun findContentChildren(g: IntArray, s: IntArray): Int {
//        g.sort()
//        s.sort()
//
//        var i = 0
//        var j = 0
//        while (i <= g.lastIndex && j <= s.lastIndex) {
//            if (g[i] <= s[j]) {
//                i++
//            }
//            j++
//        }
//        return i
//    }
//}

// 이진 bisetRight를 이용해서 원하는 값보다 큰 인덱스를 찾고 찾은 사람 수와 비교해서 결과를 얻을 수 있음.
class Solution {
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        g.sort()
        s.sort()

        var result = 0
        for (i in s) {
            val index = bisectRight(g, i)
            if (index > result) {
                result++
            }
        }
        return result
    }

    fun bisectRight(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size

        while (left < right) {
            val mid = left + ((right - left) / 2)
            if (arr[mid] <= target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}

//val g = intArrayOf(1, 2, 3)
//val s = intArrayOf(1, 1)

val g = intArrayOf(1, 2)
val s = intArrayOf(1, 2, 3)

println(Solution().findContentChildren(g, s))


//val test = intArrayOf(1,1,1,2,2,2,3,3,3)
//println(Solution().bisectRight(test, 0))

#%%
import kotlin.math.max

data class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

//class Solution {
//    fun maxLevelSum(root: TreeNode?): Int {
//        if (root == null) {
//            return 0
//        }
//        val sizes = mutableMapOf<Int, Int>()
//        val queue = mutableListOf<Pair<Int, TreeNode>>()
//        queue.add(1 to root)
//
//        while (queue.isNotEmpty()) {
//            val (level, node) = queue.removeAt(0)
//            val size = sizes.getOrDefault(level, 0) + node.`val`
//            sizes[level] = size
//            if (node.left != null) {
//                queue.add(level + 1 to node.left!!)
//            }
//            if (node.right != null) {
//                queue.add(level + 1 to node.right!!)
//            }
//        }
//
//        return sizes.maxBy { it.value }.key
//    }
//}

class Solution {
    fun maxLevelSum(root: TreeNode?): Int {
        return with(mutableListOf<TreeNode>()) {
            root?.let { add(it) }

            generateSequence {
                if (isEmpty()) {
                    null
                } else {
                    (0..lastIndex).map {
                        with(removeAt(0)) {
                            `val`.also {
                                left?.let { add(it) }
                                right?.let { add(it) }
                            }
                        }
                    }.sum()
                }
            }.withIndex()
                .map { (index, value) -> IndexedValue(index + 1, value) }
                .maxBy { it.value }
                .index
        }
    }
}

//val head = TreeNode(1)
//head.left = TreeNode(7)
//head.right = TreeNode(0)
//head.left?.left = TreeNode(7)
//head.left?.right = TreeNode(-8)

//[1,0,0,7,8,null,null]
//val head = TreeNode(1)
//head.left = TreeNode(0)
//head.right = TreeNode(0)
//head.left?.left = TreeNode(7)
//head.left?.right = TreeNode(8)

//[989,null,10250,98693,-89388,null,null,null,-32127]
val head = TreeNode(989)
head.right = TreeNode(10250)
head.right?.left = TreeNode(98693)
head.right?.right = TreeNode(-89388)
head.right?.right?.right = TreeNode(-32127)


println(Solution().maxLevelSum(head))
#%%
// 다이나믹 프로그래밍으로 풀이. 이전에 본 값의 내용을 기억할 필요가 없어 map을 쓰지 않음.

//class Solution {
//    fun majorityElement(nums: IntArray): Int {
//        val seen = mutableSetOf<Int>()
//        for (num in nums) {
//            if (seen.contains(num)) {
//                continue
//            }
//            if (nums.count { it == num } > (nums.size / 2)) {
//                return num
//            }
//            seen.add(num)
//        }
//        return -1
//    }
//}

// 분할정복으로도 풀수 있는데 효과적이지는 않음.

//class Solution {
//    fun majorityElement(nums: IntArray): Int {
//        if (nums.size == 1) {
//            return nums[0]
//        }
//        val mid = nums.size / 2
//        val a = majorityElement(nums.sliceArray(0 until mid))
//        val b = majorityElement(nums.sliceArray(mid until nums.size))
//        return if (nums.count { it==a } > mid) a else b
//    }
//}

// Sort로 풀수 있음.

class Solution {
    fun majorityElement(nums: IntArray): Int {
        return nums.sorted()[nums.size / 2]
    }
}

val nums = intArrayOf(3, 2, 3)

//val nums = intArrayOf(2, 2, 1, 1, 1, 2, 2)
println(Solution().majorityElement(nums))
#%%
class Solution {
    fun compute(list1: List<Int>, list2: List<Int>, op: Char): List<Int> {
        val result = mutableListOf<Int>()
        for (l in list1) {
            for (r in list2) {
                result.add(
                    when (op) {
                        '+' -> l + r
                        '-' -> l - r
                        else -> l * r
                    }
                )
            }
        }
        return result
    }

    fun diffWaysToCompute(expression: String): List<Int> {
        if (expression.length == 0) {
            return listOf()
        }
        expression.toIntOrNull()?.let {
            return listOf(it)
        }
        val result = mutableListOf<Int>()
        for ((index, ch) in expression.withIndex()) {
            if ("-+*".contains(ch)) {
                val list1 = diffWaysToCompute(expression.substring(0, index))
                val list2 = diffWaysToCompute(expression.substring(index + 1))
                val computed = compute(list1, list2, ch)
                result.addAll(computed)
            }
        }
        return result
    }
}

//val expression = "2-1-1"
val expression = "2*3-4*5"

println(Solution().diffWaysToCompute(expression))
#%%
class Solution {
    fun numOfWays(nums: IntArray): Int {
        val original = makeBST(nums)
        var count = 0
        fun visit(bst: MutableList<Int>, remain: List<Int>, num: Int) {
            val index = insertNumberInBST(bst, num)
            if (original.lastIndex < index || original[index] != num) {
//                println("failed: $index:$num $bst $original")
                return
            }

//            println("bst: $bst, remain: $remain, num: $num")
            if (remain.isEmpty()) {
                count++
//                println("OK: $bst $num")
                return
            }

            for (candidate in remain) {
                visit(bst.toMutableList(), remain.filter { it != candidate }, candidate)
            }
        }
        visit(mutableListOf(), nums.slice(1 until nums.size), nums[0])
        return count - 1
    }

    fun makeBST(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        for (num in nums) {
            insertNumberInBST(result, num)
        }
        return result
    }

    private fun insertNumberInBST(result: MutableList<Int>, num: Int): Int {
        var index = 1
        while (true) {
            while (result.lastIndex < index) {
                result.add(0)
            }
            val current = result[index]
            if (current == 0) {
                result[index] = num
                return index
            }
            if (num < current) {
                index *= 2
            } else if (current < num) {
                index = index * 2 + 1
            }
        }
    }
}

//val nums = intArrayOf(2, 1, 3)
//val nums = intArrayOf(3, 4, 5, 1, 2)
//val nums = intArrayOf(1,2,3)

val nums = intArrayOf(9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18)
println(Solution().numOfWays(nums))
#%%
// 상향식 메모이제이션

//class Solution {
//    val fb = mutableListOf<Int>()
//    init {
//        fb.add(0)
//        fb.add(1)
//    }
//
//    fun fib(n: Int): Int {
//        for (i in 2..n) {
//            fb.add(fb[i -1] + fb[i - 2])
//        }
//        return fb[n]
//    }
//}

// 하향식 메모이제이션 하향식이 성능이 좀 더 낫네.
//class Solution {
//    val fb = mutableMapOf<Int, Int>()
//    init {
//        fb[0] = 0
//        fb[1] = 1
//    }
//
//
//    fun fib(n: Int): Int {
//        if (fb.contains(n)) {
//            return fb.getValue(n)
//        }
//        fb[n] = fib(n - 1) + fib(n - 2)
//        return fb.getValue(n)
//    }
//}
//val n = 2
//val n = 3

// 배열을 사용하지 않는 풀이
class Solution {

    fun fib(n: Int): Int {
        var x = 0
        var y = 1
        for (i in 0 until n) {
            var temp = x + y
            x = y
            y = temp
        }
        return x
    }
}
val n = 4
println(Solution().fib(n))
#%%
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

#%%
// 일반적인 풀이

//import kotlin.math.max
//
//class Solution {
//    fun maxSubArray(nums: IntArray): Int {
//        var maxValue = nums[0]
//        for (i in 1..nums.lastIndex) {
//            nums[i] += if (nums[i - 1] < 0) 0 else nums[i - 1]
//            maxValue = max(maxValue, nums[i])
//        }
//        return maxValue
//    }
//}

// 카데인 알고리즘

import kotlin.math.max

class Solution {
    fun maxSubArray(nums: IntArray): Int {
        var currentMax = nums[0]
        var maxValue = currentMax
        for (i in 1..nums.lastIndex) {
            currentMax = max(nums[i], currentMax + nums[i])
            maxValue = max(maxValue, nums[i])
        }
        return maxValue
    }
}

//val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
//val nums = intArrayOf(1)
val nums = intArrayOf(5, 4, -1, 7, 8)

println(Solution().maxSubArray(nums))
#%%
class Solution {
    val dp = mutableMapOf<Int, Int>()
    fun climbStairs(n: Int): Int {
        if (n <= 2) {
            return n
        }
        if (dp.contains(n)) {
            return dp.getValue(n)
        }
        val result = climbStairs(n - 1) + climbStairs(n - 2)
        dp[n] = result
        return result
    }
}

val n = 1
//val n = 2
println(Solution().climbStairs(n))
#%%
import kotlin.math.max

// 메모이제이션

//class Solution {
//    fun rob(nums: IntArray): Int {
//        val dp = mutableMapOf<Int, Int>()
//        fun rob(i: Int): Int {
//            if (i < 0) {
//                return 0
//            }
//            if (dp.contains(i)) {
//                return dp.getValue(i)
//            }
//            val result = max(rob(i - 1), rob(i - 2) + nums[i])
//            dp[i] = result
//            return result
//        }
//        return rob(nums.lastIndex)
//    }
//}

// 타블레이션

class Solution {
    fun rob(nums: IntArray): Int {
        val dp = mutableListOf<Int>()
        dp.add(nums[0])
        if (nums.size == 1) {
            return dp[0]
        }
        dp.add(max(nums[0], nums[1]))
        if (nums.size == 2) {
            return dp[1]
        }
        for (i in 2..nums.lastIndex) {
            dp.add(max(dp[i - 1], dp[i - 2] + nums[i]))
        }
        return dp[dp.lastIndex]
    }
}

//val nums = intArrayOf(1,2,3,1)
val nums = intArrayOf(2, 7, 9, 3, 1)
println(Solution().rob(nums))
#%%
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

#%%
fun solution(game: String): Int {
    var num = 0
    var prevNum = 0
    var sum = 0
    for ((i, ch) in game.withIndex()) {
        if (ch.isDigit()) {
            if (i != 0 && !(game[i - 1].isDigit())) {
                sum += num
                prevNum = num
                num = 0
            }
            num = (num * 10) + ch.toString().toInt()
        }
        when (ch) {
            'S' -> Unit
            'D' -> num = num * num
            'T' -> num = num * num * num
            '*' -> {
                sum += prevNum
                num = num * 2
            }
            '#' -> {
                num *= -1
            }
            else -> Unit
        }
//        println("num: $num sum: $sum")
    }
    sum += num
    return sum
}

val game1 = "1S2D*3T"
println(solution(game1))

val game2 = "1D2S#10S"
println(solution(game2))

val game3 = "1D2S0T"
println(solution(game3))

val game4 = "1S*2T*3S"
println(solution(game4))

val game5 = "1D#2S*3S"
println(solution(game5))

val game6 = "1T2D3D#"
println(solution(game6))

val game7 = "1D2S3T*"
println(solution(game7))
#%%
class Solution {
    fun maximumWealth(accounts: Array<IntArray>): Int {
        return accounts.map { it.sum() }.max()
    }
}

//val accounts = arrayOf(intArrayOf(1,2,3),intArrayOf(3,2,1))

//val accounts = arrayOf(intArrayOf(1, 5), intArrayOf(7, 3), intArrayOf(3, 5))

val accounts = arrayOf(intArrayOf(2,8,7), intArrayOf(7,1,3),intArrayOf(1,9,5))
println(Solution().maximumWealth(accounts))
#%%
class Solution {
    fun fizzBuzz(n: Int): List<String> {
        return (1..n).map {
            if (it % 3 == 0 && it % 5 == 0) {
                "FizzBuzz"
            } else if (it % 3 == 0) {
                "Fizz"
            } else if (it % 5 == 0) {
                "Buzz"
            } else {
                it.toString()
            }
        }
    }
}

println(Solution().fizzBuzz(3))
println(Solution().fizzBuzz(5))
println(Solution().fizzBuzz(15))

#%%
